package com.ebac.streaming_api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @PostMapping
    public Filme criar(@Valid @RequestBody Filme filme) {
        return repository.save(filme);
    }

    @GetMapping
    public List<Filme> listar() {
        return repository.findAll();
    }
}