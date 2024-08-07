package com.wise.forms_coleta.entities;

import com.wise.forms_coleta.dtos.ponto.PontoCreateDTO;
import com.wise.forms_coleta.entities.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Ponto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Ponto(PontoCreateDTO data){
        this.nome = data.nome();
        this.localizacao = data.localizacao();
        this.excel = data.excel();
        this.statusEnum = StatusEnum.ATIVO;
    }


}
