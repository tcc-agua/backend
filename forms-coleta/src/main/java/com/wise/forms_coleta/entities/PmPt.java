package com.wise.forms_coleta.entities;

import com.wise.forms_coleta.dtos.PmPt.PmPtCreateDTO;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_coleta", referencedColumnName = "id")
    private Coleta fk_coleta;


     public PmPt(PmPtCreateDTO data){
         this.nivelAgua = data.nivelAgua();
         this.nivelOleo = data.nivelOleo();
         this.flRemoManual = data.flRemoManual();
     }
}
