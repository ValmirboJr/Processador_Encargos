package org.example.processador_encargos.adapters.inbound.batch;

import org.example.processador_encargos.domain.Lancamento;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job processarEncargosJob(JobRepository jobRepository, Step masterStep) {
        return new JobBuilder("processarEncargosJob", jobRepository)
                .start(masterStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step masterStep(JobRepository jobRepository,
                           FilePartitioner partitioner,
                           Step workerStep) {
        return new StepBuilder("masterStep", jobRepository)
                .partitioner("workerStep", partitioner)
                .step(workerStep)
                .gridSize(32)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Step workerStep(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           LancamentoReader reader,
                           LancamentoWriter writer) {
        return new StepBuilder("workerStep", jobRepository)
                .<Lancamento, Lancamento>chunk(1000, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<Lancamento> reader() {
        return new FlatFileItemReaderBuilder<Lancamento>()
                .name("lancamentoReader")
                .resource(new FileSystemResource("files/lancamentos.csv"))
                .comments("--")
                .delimited()
                .names("id", "contaId", "valor", "encargos")
                .targetType(Lancamento.class)
                .build();
    }

    @Bean
    public ItemWriter<Lancamento> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Lancamento>()
                .dataSource(dataSource)
                .sql("""
                INSERT INTO lancamentos(id, conta_id, valor, encargos, situacao_conta, processamento, criado_em, processado_em)
                VALUES(:id, :contaId, :valor, :encargos, :situacaoConta, :processamentopg, :criadoEm, :processadoEm)""")
                .itemSqlParameterSourceProvider(
                        new BeanPropertyItemSqlParameterSourceProvider<>()
                )
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(32);
        executor.setMaxPoolSize(32);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("batch-worker-");
        executor.initialize();
        return executor;
    }
}