package com.ebac.streaming_api;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;

    private LocalDateTime dataVisualizacao;

    public Historico(Usuario usuario, Filme filme) {
        this.usuario = usuario;
        this.filme = filme;
        this.dataVisualizacao = LocalDateTime.now();
    }
}