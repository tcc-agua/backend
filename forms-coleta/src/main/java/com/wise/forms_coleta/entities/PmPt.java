package com.wise.forms_coleta.entities;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    private Ponto ponto;

    @ManyToMany(mappedBy = "pmPtSet", fetch = FetchType.EAGER)
    private Set<Coleta> coletas = new HashSet<>();

     public PmPt(PmPtCreateDTO data){
         this.nivel_agua = data.nivelAgua();
         this.nivel_oleo = data.nivelOleo();
         this.fl_remo_manual = data.flRemoManual();
     }
}
