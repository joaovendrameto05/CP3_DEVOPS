package com.dimdim.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dimdim.api.model.Lancamento;
import com.dimdim.api.repository.LancamentoRepository;

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
}