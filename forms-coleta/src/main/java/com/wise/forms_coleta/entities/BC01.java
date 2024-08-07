package com.wise.forms_coleta.entities;

import com.wise.forms_coleta.dtos.bc01.BC01CreateDTO;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_ponto", referencedColumnName = "id")
    private Ponto fk_ponto;

    private int horimetro;
    private double pressao;
    private int frequencia;
    private double vazao;
    private int volume;

    public BC01(BC01CreateDTO data) {
        this.horimetro = data.horimetro();
        this.pressao = data.pressao();
        this.frequencia = data.frequencia();
        this.vazao = data.vazao();
        this.volume = data.volume();
    }
}
