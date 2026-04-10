package org.example.processador_encargos.adapters.inbound.batch;

import org.example.processador_encargos.domain.Lancamento;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class LancamentoReader implements ItemReader<Lancamento> {

    private FlatFileItemReader<Lancamento> delegate;

    @BeforeStep
    public void init(StepExecution stepExecution) {
        String path = stepExecution.getJobParameters().getString("filePath");

        this.delegate = new FlatFileItemReaderBuilder<Lancamento>()
                .name("lancamentoReader")
                .resource(new FileSystemResource(path))
                .delimited()
                .delimiter(";")
                .names("contaId", "valor", "encargos")
                .targetType(Lancamento.class)
                .saveState(false)
                .build();

        this.delegate.open(stepExecution.getExecutionContext());
    }

    @Override
    public Lancamento read() throws Exception {
        return delegate.read();
    }
}
