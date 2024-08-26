package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wise.forms_coleta.dtos.pbs.PbCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pbs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PBs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double pressao;
    private Double pulsos;
    private Double nivel_oleo;
    private Double nivel_agua;
    private Double vol_rem_oleo;

//    Relacionamentos

    @ManyToOne
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    @JsonBackReference
    private Ponto ponto;

    @ManyToMany(mappedBy = "pbSet", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Coleta> coletas = new HashSet<>();

    public PBs(PbCreateDTO data) {
        this.pressao = data.pressao();
        this.pulsos = data.pulsos();
        this.nivel_oleo = data.nivel_oleo();
        this.nivel_agua = data.nivel_agua();
        this.vol_rem_oleo = data.vol_rem_oleo();
    }
}
