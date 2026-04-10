package org.example.processador_encargos.domain;

import org.example.processador_encargos.domain.utils.Encargos;
import org.example.processador_encargos.domain.utils.Processamentopg;
import org.example.processador_encargos.domain.utils.SituacaoConta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Lancamento {

    private UUID id;
    private long contaId;
    private BigDecimal valor;
    private Encargos encargos;
    private SituacaoConta statusconta;
    private Processamentopg processamentopg;
    private LocalDateTime criadoEm;
    private LocalDateTime processadoEm;

    public Lancamento() {}

    public Lancamento(UUID id, long contaId,BigDecimal valor,Encargos encargos,SituacaoConta statusconta,Processamentopg  processamentopg,LocalDateTime criadoEm,LocalDateTime processadoEm ) {
        this.id = id;
        this.contaId = contaId;
        this.valor = valor;
        this.encargos = encargos;
        this.statusconta = statusconta;
        this.processamentopg = processamentopg;
        this.criadoEm = criadoEm;
        this.processadoEm = processadoEm;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Encargos getEncargos() {
        return encargos;
    }

    public void setEncargos(Encargos encargos) {
        this.encargos = encargos;
    }

    public SituacaoConta getStatusconta() {
        return statusconta;
    }

    public void setStatusconta(SituacaoConta statusconta) {
        this.statusconta = statusconta;
    }

    public Processamentopg getProcessamentopg() {
        return processamentopg;
    }

    public void setProcessamentopg(Processamentopg processamentopg) {
        this.processamentopg = processamentopg;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getProcessadoEm() {
        return processadoEm;
    }

    public void setProcessadoEm(LocalDateTime processadoEm) {
        this.processadoEm = processadoEm;
    }
}
