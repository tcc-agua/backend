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

    @Column(name = "nivel_agua")
    private Double  nivelAgua;

    @Column(name = "nivel_oleo")
    private Double nivelOleo;

    @Column(name = "fl_remo_manual")
    private Double flRemoManual;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_ponto", referencedColumnName = "id")
    private Ponto fk_ponto;

    @OneToMany(mappedBy = "pmPt", cascade = CascadeType.ALL)
    @JoinColumn(name="coleta_id", referencedColumnName = "id")
    private Set<Coleta> coletas = new HashSet<>();


     public PmPt(PmPtCreateDTO data){
         this.nivelAgua = data.nivelAgua();
         this.nivelOleo = data.nivelOleo();
         this.flRemoManual = data.flRemoManual();
     }
}
