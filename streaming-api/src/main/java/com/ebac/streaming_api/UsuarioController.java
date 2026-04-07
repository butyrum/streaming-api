package com.ebac.streaming_api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public Usuario criar(@Valid @RequestBody Usuario usuario) {

        return service.salvar(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listarTodos();
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}