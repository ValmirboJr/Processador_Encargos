package org.example.processador_encargos.adapters.outbound.kafka;

import lombok.RequiredArgsConstructor;
import org.example.processador_encargos.domain.Lancamento;
import org.example.processador_encargos.domain.event.LancamentoContabilEvent;
import org.example.processador_encargos.domain.event.RequestStatusEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LancamentoKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publicarRequestStatus(Lancamento lancamento) {
        RequestStatusEvent event = RequestStatusEvent.builder()
                .contaId(lancamento.getContaId())
                .lancamentoId(lancamento.getId())
                .build();

        kafkaTemplate.send("request-status-conta",
                lancamento.getContaId().toString(), event);
    }

    public void publicarLancamentoContabil(Lancamento lancamento) {
        LancamentoContabilEvent event = LancamentoContabilEvent.builder()
                .lancamentoId(lancamento.getId())
                .contaId(lancamento.getContaId())
                .valor(lancamento.getValor())
                .encargos(lancamento.getEncargos())
                .build();

        kafkaTemplate.send("lancamento-contabil",
                lancamento.getId().toString(), event);
    }
}