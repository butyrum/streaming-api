package com.ebac.streaming_api;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O gênero é obrigatório")
    private String genero;

    @NotNull(message = "A classificação etária é obrigatória")
    private Integer classificacaoEtaria;

    @NotBlank(message = "O plano mínimo é obrigatório")
    private String planoMinimo;
}