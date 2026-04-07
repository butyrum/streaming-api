package com.ebac.streaming_api;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String perfil;
    private String nome;

    @Min(value = 0, message = "Idade não pode ser negativa")
    @Max(value = 55, message = "A idade máxima permitida é 55 anos")
    private int idade;

    private boolean assinante;

    @Enumerated(EnumType.STRING)
    private TipoPlano plano;


    @ManyToOne
    @JoinColumn(name = "dependente_de_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario dependenteDe;

    public String getPerfil() {
        if (this.idade < 12) return "INFANTIL";
        if (this.idade < 18) return "ADOLESCENTE";
        return "ADULTO";
    }
}