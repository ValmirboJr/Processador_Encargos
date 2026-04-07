package org.example.processador_encargos.domain;

import java.util.List;
import java.util.UUID;

public interface LancamentoPort {
    void salvarTodos(List<? extends Lancamento> lancamentos);
    Lancamento buscarPorId(UUID id);
}
