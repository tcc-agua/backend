package com.wise.forms_coleta.entities;

import com.wise.forms_coleta.entities.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Ponto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ponto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String localizacao;
    private String excel;

    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;
}
