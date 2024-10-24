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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_fase_livre",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name= "fase_livre_id"))
    @JsonManagedReference
    private Set<FaseLivre> faseLivreSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_ph",
            joinColumns = @JoinColumn(name= "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "sensor_ph_id"))
    @JsonManagedReference
    private Set<SensorPH> phSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_tq01",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "tq01_id"))
    @JsonManagedReference
    private Set<TQ01> tq01Set = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_tq02",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "tq02_id"))
    @JsonManagedReference
    private Set<TQ02> tq02Set = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_bh02",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "bh02_id"))
    @JsonManagedReference
    private Set<BH02> bh02Set = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_bs01pressao",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "bs01pressao_id"))
    @JsonManagedReference
    private Set<BS01Pressao> bs01PressaoSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_tq04_tq05",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "tq04_tq05_id"))
    @JsonManagedReference
    private Set<Tq04Tq05> tq04Tq05Set = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_bomba_bc03",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "bomba_bc03_id"))
    @JsonManagedReference
    private Set<BombaBc03> bombaBc03Set = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_bs01hidrometro",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "bs01hidrometro_id"))
    @JsonManagedReference
    private Set<BS01Hidrometro> bs01HidrometroSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coleta_hidrometro",
    joinColumns = @JoinColumn(name = "coleta_id"),
    inverseJoinColumns = @JoinColumn(name= "hidrometro_id"))
    @JsonManagedReference
    private Set<Hidrometro> hidrometroSet = new HashSet<>();


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
