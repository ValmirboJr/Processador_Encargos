package org.example.processador_encargos.application.service;

import lombok.RequiredArgsConstructor;
import org.example.processador_encargos.adapters.outbound.kafka.LancamentoKafkaProducer;
import org.example.processador_encargos.application.usecases.LancamentoUseCases;
import org.example.processador_encargos.domain.Lancamento;
import org.example.processador_encargos.domain.LancamentoPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LancamentoServiceImpl implements LancamentoUseCases{

    private final LancamentoPort lancamentoPort;
    private final LancamentoKafkaProducer kafkaProducer;

    @Override
    public void processarLote(List<Lancamento> lancamentos) {
       lancamentos.forEach(l -> l.setCriadoEm(LocalDateTime.now()));
        lancamentoPort.salvarTodos(lancamentos);
        lancamentos.forEach(kafkaProducer::publicarRequestStatus);
    }

    @Override
    public Lancamento buscarPorId(UUID id) {
        return lancamentoPort.buscarPorId(id);
    }
}
