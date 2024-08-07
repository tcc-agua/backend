package com.wise.forms_coleta.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="BC-01")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BC01 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int horimetro;
    private double pressao;
    private int frequencia;
    private double vazao;
    private int volume;

}
