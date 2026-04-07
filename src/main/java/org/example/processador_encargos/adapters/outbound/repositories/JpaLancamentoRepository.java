package org.example.processador_encargos.adapters.outbound.repositories;


import org.example.processador_encargos.adapters.outbound.entities.JpaLancamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaLancamentoRepository extends JpaRepository<JpaLancamentoEntity, UUID> {
}
