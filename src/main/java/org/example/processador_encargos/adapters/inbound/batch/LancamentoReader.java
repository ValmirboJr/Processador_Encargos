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
        ExecutionContext ctx = stepExecution.getExecutionContext();
        String filePath = ctx.getString("filePath");

        this.delegate = new FlatFileItemReaderBuilder<Lancamento>()
                .name("lancamentoReader")
                .resource(new FileSystemResource(filePath))
                .linesToSkip(0)
                .delimited()
                .delimiter(";")
                .names("id", "contaId", "valor", "encargos")
                .targetType(Lancamento.class)
                .build();

        this.delegate.open(ctx);
    }

    @Override
    public Lancamento read() throws Exception {
        return delegate.read();
    }
}
