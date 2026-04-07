package org.example.processador_encargos.adapters.outbound.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.processador_encargos.domain.utils.Encargos;
import org.example.processador_encargos.domain.utils.Processamentopg;
import org.example.processador_encargos.domain.utils.SituacaoConta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "lancamento_tb")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JpaLancamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID contaId;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Encargos encargos;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoConta statusconta;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Processamentopg processamentopg;
    private LocalDateTime criadoEm;
    private LocalDateTime processadoEm;
}
