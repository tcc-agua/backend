package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wise.forms_coleta.dtos.ponto.PontoCreateDTO;
import com.wise.forms_coleta.entities.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(mappedBy = "ponto")
    private ColunasCarvao colunas_carvao;

    @OneToOne(mappedBy = "ponto")
    private PmPt pm_pt;

    @OneToOne(mappedBy = "ponto")
    private BC06 bc06;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="excel_id", referencedColumnName = "id")
    @JsonManagedReference
    private Excel excel;

    public Ponto(PontoCreateDTO data){
        this.nome = data.nome();
        this.localizacao = data.localizacao();
        this.status = StatusEnum.NAO_COLETADO;
    }
}
