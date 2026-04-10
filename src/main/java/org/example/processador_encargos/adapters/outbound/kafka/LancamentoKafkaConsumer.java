package org.example.processador_encargos.adapters.outbound.kafka;

import lombok.RequiredArgsConstructor;
import org.example.processador_encargos.domain.Lancamento;
import org.example.processador_encargos.domain.LancamentoPort;
import org.example.processador_encargos.domain.utils.Processamentopg;
import org.example.processador_encargos.domain.utils.SituacaoConta;
import org.example.processador_encargos.domain.event.ResponseStatusEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LancamentoKafkaConsumer {

    private final LancamentoPort lancamentoPort;
    private final LancamentoKafkaProducer kafkaProducer;

    @KafkaListener(topics = "response-status-conta",
            groupId = "encargos-processor")
    public void receberRespostaStatus(ResponseStatusEvent event) {

        Lancamento lancamento = lancamentoPort.buscarPorId(event.getLancamentoId());

        lancamento.setStatusconta(event.getSituacaoConta());
        lancamento.setProcessadoEm(LocalDateTime.now());

        if (event.getSituacaoConta() == SituacaoConta.ATIVA) {
            lancamento.setProcessamentopg(Processamentopg.APROVADO);
            kafkaProducer.publicarLancamentoContabil(lancamento);
        } else {
            lancamento.setProcessamentopg(Processamentopg.RECUSADO);
        }

        lancamentoPort.salvarTodos(List.of(lancamento));
    }
}