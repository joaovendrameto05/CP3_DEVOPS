package com.dimdim.api.controller;

import com.dimdim.api.model.Lancamento;
import com.dimdim.api.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.授業;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoRepository repository;

    @GetMapping
    public List<Lancamento> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Lancamento criar(@RequestBody Lancamento lancamento) {
        return repository.save(lancamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> atualizar(@PathVariable Long id, @RequestBody Lancamento lancamentoAtualizado) {
        return repository.findById(id)
                .map(lancamento -> {
                    lancamento.setDescricao(lancamentoAtualizado.getDescricao());
                    lancamento.setValor(lancamentoAtualizado.getValor());
                    Lancamento salvo = repository.save(lancamento);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .map(lancamento -> {
                    repository.delete(lancamento);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}