package org.example.processador_encargos.adapters.outbound.repositories;

import jakarta.transaction.Transactional;
import org.example.processador_encargos.adapters.outbound.entities.JpaLancamentoEntity;
import org.example.processador_encargos.domain.Lancamento;
import org.example.processador_encargos.domain.LancamentoPort;
import org.example.processador_encargos.mapper.LancamentoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class LancamentoJpaAdapter implements LancamentoPort {

    private final JpaLancamentoRepository repository;
    private final LancamentoMapper lancamentoMapper;

    public LancamentoJpaAdapter(JpaLancamentoRepository repository, LancamentoMapper lancamentoMapper) {
        this.repository = repository;
        this.lancamentoMapper = lancamentoMapper;
    }

    @Override
    @Transactional
    public void salvarTodos(List<? extends Lancamento> lancamentos) {
        List<JpaLancamentoEntity> entities = lancamentos.stream()
                .map(lancamentoMapper::toEntity)
                .toList();
        repository.saveAll(entities);
    }

    @Override
    public Lancamento buscarPorId(UUID id) {
       return repository.findById(id)
               .map(lancamentoMapper::toDomain)
               .orElseThrow(() -> new RuntimeException("Lançamento não encontrado: " + id));
    }
}
