package org.example.processador_encargos.domain;

import java.util.List;

public interface LancamentoRepository {
    void salvarTodos(List<? extends Lancamento> lancamentos);
}
