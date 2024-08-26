package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wise.forms_coleta.dtos.cd.CDCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="cd")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo_rede;
    private Double pressao;
    private Integer hidrometro;

    //Relacionamentos

    @ManyToOne
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    @JsonBackReference
    private Ponto ponto;

    @ManyToMany(mappedBy = "cdSet", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Coleta> coletas = new HashSet<>();

    public CD(CDCreateDTO data) {
        this.tipo_rede = data.tipo_rede();
        this.pressao = data.pressao();
        this.hidrometro = data.hidrometro();
    }
}
