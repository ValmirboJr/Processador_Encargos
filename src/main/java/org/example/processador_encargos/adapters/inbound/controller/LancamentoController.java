package org.example.processador_encargos.adapters.inbound.controller;

import lombok.RequiredArgsConstructor;
import org.example.processador_encargos.application.usecases.LancamentoUseCases;
import org.example.processador_encargos.domain.Lancamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/lancamentos")
@RequiredArgsConstructor
public class LancamentoController {

    private final LancamentoUseCases lancamentoUseCases;

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> buscarPorId(@PathVariable UUID id) {
        Lancamento lancamento = lancamentoUseCases.buscarPorId(id);
        if (lancamento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(lancamento);
    }
}