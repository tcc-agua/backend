package com.wise.forms_coleta.entities;

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
    private String excel;

    @Enumerated(EnumType.STRING)
    @Column(name="status_enum")
    private StatusEnum statusEnum;

    @OneToOne(mappedBy = "fk_ponto")
    private ColunasCarvao colunasCarvao;

    @OneToOne(mappedBy = "fk_ponto")
    private PmPt pmPt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_excel", referencedColumnName = "id")
    private Excel fk_excel;

    public Ponto(PontoCreateDTO data){
        this.nome = data.nome();
        this.localizacao = data.localizacao();
        this.excel = data.excel();
        this.statusEnum = StatusEnum.ATIVO;
    }
}
