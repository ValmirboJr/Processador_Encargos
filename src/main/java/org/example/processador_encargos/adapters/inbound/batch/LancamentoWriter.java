package org.example.processador_encargos.adapters.inbound.batch;

import org.example.processador_encargos.domain.Lancamento;
import org.example.processador_encargos.domain.LancamentoPort;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class LancamentoWriter implements ItemWriter<Lancamento> {

    private final LancamentoPort lancamentoPort ;

    public LancamentoWriter(LancamentoPort lancamentoPort) {
        this.lancamentoPort = lancamentoPort;
    }

    @Override
    public void write(Chunk<? extends Lancamento> chunk) {
        lancamentoPort.salvarTodos(chunk.getItems());
    }
}