package org.example.processador_encargos.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.processador_encargos.domain.SituacaoConta;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespoonseStatusEvent {
    private UUID contaId;
    private UUID lancamentoId;
    private SituacaoConta situacaoConta;
}