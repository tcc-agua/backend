package com.wise.forms_coleta.entities;

import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="colunas_carvao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColunasCarvao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_ponto", referencedColumnName = "id")
    private Ponto fk_ponto;

    private Double pressao_c01;
    private Double pressao_c02;
    private Double pressao_c03;
    private Double pressao_saida;
    private Boolean houve_troca_carvao;
    private Boolean houve_retrolavagem;

    public ColunasCarvao(ColunasCarvaoCreateDTO data){
        this.pressao_c01 = data.pressao_c01();
        this.pressao_c02 = data.pressao_c02();
        this.pressao_c03 = data.pressao_c03();
        this.pressao_saida = data.pressao_saida();
        this.houve_troca_carvao = data.houve_troca_carvao();
        this.houve_retrolavagem = data.houve_retrolavagem();
    }
}
