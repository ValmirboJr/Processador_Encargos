package org.example.processador_encargos.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.processador_encargos.domain.utils.Encargos;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoContabilEvent {
    private UUID lancamentoId;
    private long contaId;
    private BigDecimal valor;
    private Encargos encargos;
}
