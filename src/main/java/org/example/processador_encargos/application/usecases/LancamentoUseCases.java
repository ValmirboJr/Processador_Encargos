package org.example.processador_encargos.application.usecases;

import org.example.processador_encargos.domain.Lancamento;

import java.util.List;
import java.util.UUID;

public interface LancamentoUseCases {

    void processarLote(List<Lancamento> lancamentos);
    Lancamento buscarPorId(UUID id);
}
