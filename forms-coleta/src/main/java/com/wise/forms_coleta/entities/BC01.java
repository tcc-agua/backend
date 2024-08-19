package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wise.forms_coleta.dtos.bc01.BC01CreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="bc01")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BC01 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer horimetro;
    private Double pressao;
    private Integer frequencia;
    private Double vazao;
    private Integer volume;

    //Relacionamentos

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    @JsonBackReference
    private Ponto ponto;

    @ManyToMany(mappedBy = "BC01Set", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Coleta> coletas = new HashSet<>();

    public BC01(BC01CreateDTO data) {
        this.horimetro = data.horimetro();
        this.pressao = data.pressao();
        this.frequencia = data.frequencia();
        this.vazao = data.vazao();
        this.volume = data.volume();
    }
}
