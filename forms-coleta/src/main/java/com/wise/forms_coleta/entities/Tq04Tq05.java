package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05CreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tq04_tq05")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tq04Tq05 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean houve_preparo_solucao;
    private Integer qtd_bombonas;
    private Double kg_bombonas;
    private Double horimetro;
    private Double hidrometro;


//    Relacionamentos

    @ManyToOne
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    @JsonBackReference
    private Ponto ponto;

    @ManyToMany(mappedBy = "tq04Tq05Set", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Coleta> coletas = new HashSet<>();

    public Tq04Tq05(Tq04Tq05CreateDTO data) {
        this.hidrometro = data.hidrometro();
        this.horimetro = data.horimetro();
        this.kg_bombonas = data.kg_bombonas();
        this.qtd_bombonas = data.qtd_bombonas();
        this.houve_preparo_solucao = data.houve_preparo_solucao();
    }
}
