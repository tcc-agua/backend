package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wise.forms_coleta.dtos.PmPt.PmPtCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pm_pt")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PmPt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double  nivel_agua;
    private Double nivel_oleo;
    private Double fl_remo_manual;

//    Relacionamentos

    @ManyToOne
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    @JsonBackReference
    private Ponto ponto;

    @ManyToMany(mappedBy = "pmPtSet", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Coleta> coletas = new HashSet<>();

     public PmPt(PmPtCreateDTO data){
         this.nivel_agua = data.nivelAgua();
         this.nivel_oleo = data.nivelOleo();
         this.fl_remo_manual = data.flRemoManual();
     }
}
