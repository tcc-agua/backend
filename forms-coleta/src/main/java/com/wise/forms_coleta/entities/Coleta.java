package com.wise.forms_coleta.entities;

import com.wise.forms_coleta.dtos.coleta.ColetaCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Coleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tecnico;
    private LocalDate data_coleta;
    private LocalTime hora_inicio;
    private LocalTime hora_fim;

//    Relacionamentos

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_colunas_carvao",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "colunas_carvao_id"))
    private Set<ColunasCarvao> colunasCarvaoSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_pmpt",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "pm_pt_id"))
    private Set<PmPt> pmPtSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_bc06",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "bc06_id"))
    private Set<BC06> bc06Set = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_BC01",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name= "BC01_id"))
    private Set<BC01> BC01Set = new HashSet<>();

    public Coleta(ColetaCreateDTO data){
        this.tecnico = data.tecnico();
        this.data_coleta = data.dataColeta();
        this.hora_inicio = data.horaInicio();
        this.hora_fim = data.horaFim();
    }
}
