package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wise.forms_coleta.dtos.ponto.PontoCreateDTO;
import com.wise.forms_coleta.entities.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Ponto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String localizacao;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

//    Relacionamentos

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ColunasCarvao> colunasCarvaoSet = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<PmPt> pmPtSet = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<BC06> bc06Set = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="excel_id", referencedColumnName = "id")
    @JsonManagedReference
    private Excel excel;

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<BC01> bc01Set = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<PBs> pBsSet = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<FiltroCartucho> filtroCartuchoSet = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<FaseLivre> faseLivreSet = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<TQ02> tq02Set = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<TQ01> tq01Set = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Tq04Tq05> tq04Tq05Set = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<BombaBc03> bombaBc03Set = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<CD> cdSet = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<BH02> bh02Set = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<BS01Pressao> bs01PressaoSet = new HashSet<>();

    @OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<BS01Hidrometro> bs01HidrometroSet = new HashSet<>();

    public Ponto(PontoCreateDTO data){
        this.nome = data.nome();
        this.localizacao = data.localizacao();
        this.status = StatusEnum.NAO_COLETADO;
    }
}
