package org.example.processador_encargos.mapper;

import org.example.processador_encargos.adapters.outbound.entities.JpaLancamentoEntity;
import org.example.processador_encargos.domain.Lancamento;
import org.springframework.stereotype.Component;

@Component
public class LancamentoMapper {

    public JpaLancamentoEntity toEntity(Lancamento lancamento) {
        return JpaLancamentoEntity.builder()
                .id(lancamento.getId())
                .contaId(lancamento.getContaId())
                .valor(lancamento.getValor())
                .encargos(lancamento.getEncargos())
                .statusconta(lancamento.getStatusconta())
                .processamentopg(lancamento.getProcessamentopg())
                .criadoEm(lancamento.getCriadoEm())
                .processadoEm(lancamento.getProcessadoEm())
                .build();
    }

    public Lancamento toDomain(JpaLancamentoEntity entity) {
        return new Lancamento(
                entity.getId(),
                entity.getContaId(),
                entity.getValor(),
                entity.getEncargos(),
                entity.getStatusconta(),
                entity.getProcessamentopg(),
                entity.getCriadoEm(),
                entity.getProcessadoEm()
        );
    }
}