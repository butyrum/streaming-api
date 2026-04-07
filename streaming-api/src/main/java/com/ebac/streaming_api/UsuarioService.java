package com.ebac.streaming_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {

        if (usuario.isAssinante()) {
            if (usuario.getIdade() < 18) {
                throw new RuntimeException("Apenas maiores de 18 anos podem ser assinantes.");
            }
            if (usuario.getIdade() > 55) {
                throw new RuntimeException("Idade acima do limite permitido (55 anos).");
            }
        }


        if (!usuario.isAssinante()) {
            if (usuario.getDependenteDe() == null || usuario.getDependenteDe().getId() == null) {
                throw new RuntimeException("Um dependente deve estar vinculado a um assinante.");
            }


            Usuario assinantePai = repository.findById(usuario.getDependenteDe().getId())
                    .orElseThrow(() -> new RuntimeException("Assinante pai não encontrado!"));

            if (!assinantePai.isAssinante()) {
                throw new RuntimeException("O usuário de destino não é um assinante principal.");
            }


            long dependentesAtuais = repository.countByDependenteDeId(assinantePai.getId());
            int limiteDoPlano = definirLimitePorPlano(assinantePai.getPlano().name());

            if (dependentesAtuais >= limiteDoPlano) {
                throw new RuntimeException("Limite de dependentes atingido para o plano " + assinantePai.getPlano());
            }
            // ----------------------------------------------

            usuario.setDependenteDe(assinantePai);
        }


        usuario.setPerfil(definirPerfilPorIdade(usuario.getIdade()));

        return repository.save(usuario);
    }

    private int definirLimitePorPlano(String plano) {
        return switch (plano) {
            case "BASIC" -> 0;
            case "STANDARD" -> 2;
            case "PREMIUM" -> 4;
            default -> 0;
        };
    }

    private String definirPerfilPorIdade(int idade) {
        if (idade < 12) return "INFANTIL";
        if (idade < 18) return "ADOLESCENTE";
        return "ADULTO";
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        repository.deleteById(id);
    }
}