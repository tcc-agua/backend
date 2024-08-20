package com.wise.forms_coleta.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wise.forms_coleta.dtos.bh02.BH02CreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "bh02")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class BH02 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer horimetro;
    private Integer pressao;
    private Integer frequencia;

    // Relacionamentos

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ponto_id", referencedColumnName = "id")
    @JsonBackReference
    private Ponto ponto;

    @ManyToMany(mappedBy = "bh02Set", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Coleta> coletas = new HashSet<>();

    public BH02(BH02CreateDTO data) {
        this.horimetro = data.horimetro();
        this.frequencia = data.frequencia();
        this.pressao = data.pressao();
    }
}
