package org.example.processador_encargos.adapters.outbound.repositories;

import org.example.processador_encargos.domain.LancamentoPort;
import org.springframework.stereotype.Component;

@Component
public class LancamentoJpaAdapter implements LancamentoPort {

    private final JpaLancamentoRepository repository;

    public LancamentoJpaAdapter(JpaLancamentoRepository repository) {
        this.repository = repository;
    }
}
