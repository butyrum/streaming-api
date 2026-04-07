package com.ebac.streaming_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StreamingService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    public String assistir(Long usuarioId, Long filmeId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Filme filme = filmeRepository.findById(filmeId)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        String planoDaConta = String.valueOf(usuario.isAssinante() ? usuario.getPlano() : usuario.getDependenteDe().getPlano());
        validarPlano(planoDaConta, filme.getPlanoMinimo());


        validarIdade(usuario, filme.getClassificacaoEtaria());


        int segundos = (int) (Math.random() * 11);


        Historico registro = new Historico(usuario, filme);
        historicoRepository.save(registro);

        return "Sucesso! Você assistiu '" + filme.getTitulo() + "' por " + segundos + " segundos.";
    }

    private void validarPlano(String planoUsuario, String planoFilme) {

        if (planoFilme.equals("PREMIUM") && !planoUsuario.equals("PREMIUM")) {
            throw new RuntimeException("Este filme é exclusivo para assinantes PREMIUM.");
        }
        if (planoFilme.equals("STANDARD") && planoUsuario.equals("BASIC")) {
            throw new RuntimeException("Seu plano BASIC não permite assistir este filme.");
        }
    }

    private void validarIdade(Usuario usuario, Integer classificacao) {
        String perfil = usuario.getPerfil();

        if (perfil.equals("INFANTIL") && classificacao > 12) {
            throw new RuntimeException("Perfil Infantil não tem acesso a filmes acima de 12 anos.");
        }
        if (perfil.equals("ADOLESCENTE") && classificacao > 16) {

            throw new RuntimeException("Perfil Adolescente não tem acesso a filmes para adultos.");
        }
    }

    public List<Historico> buscarHistorico(Long usuarioId) {
        return historicoRepository.findByUsuarioIdOrderByDataVisualizacaoDesc(usuarioId);
    }
}