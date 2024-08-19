package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name="data_coleta")
    private LocalDate dataColeta;
    private LocalTime hora_inicio;
    private LocalTime hora_fim;

//    Relacionamentos

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_colunas_carvao",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "colunas_carvao_id"))
    @JsonManagedReference // Marca o lado gerenciado
    private Set<ColunasCarvao> colunasCarvaoSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_pmpt",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "pm_pt_id"))
    @JsonManagedReference
    private Set<PmPt> pmPtSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_bc06",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "bc06_id"))
    @JsonManagedReference
    private Set<BC06> bc06Set = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_BC01",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name= "BC01_id"))
    @JsonManagedReference
    private Set<BC01> BC01Set = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_pbs",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name= "pbs_id"))
    @JsonManagedReference
    private Set<PBs> pbSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_horimetro",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name= "horimetro_id"))
    @JsonManagedReference
    private Set<Horimetro> horimetroSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_filtro_cartucho",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name= "filtro_cartucho_id"))
    @JsonManagedReference
    private Set<FiltroCartucho> filtroCartuchoSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_cd",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name= "cd_id"))
    @JsonManagedReference
    private Set<CD> cdSet = new HashSet<>();

    public Coleta(ColetaCreateDTO data){
        this.tecnico = data.tecnico();
        this.dataColeta = data.dataColeta();
        this.hora_inicio = data.horaInicio();
        this.hora_fim = data.horaFim();
    }

    public Coleta(String nomeTecnico, LocalDate data_coleta, LocalTime hora_inicio){
        this.tecnico = nomeTecnico;
        this.dataColeta = data_coleta;
        this.hora_inicio = hora_inicio;
    }
}
