package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="colunas_carvao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ColunasCarvao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double pressao_c01;
    private Double pressao_c02;
    private Double pressao_c03;
    private Double pressao_saida;
    private Boolean houve_troca_carvao;
    private Boolean houve_retrolavagem;

//    Relacionamentos

    @ManyToOne
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    @JsonBackReference
    private Ponto ponto;

    @ManyToMany(mappedBy = "colunasCarvaoSet", fetch = FetchType.EAGER)
    @JsonBackReference // Marca o lado de referência
    private Set<Coleta> coletas = new HashSet<>();

    public ColunasCarvao(ColunasCarvaoCreateDTO data){
        this.pressao_c01 = data.pressao_c01();
        this.pressao_c02 = data.pressao_c02();
        this.pressao_c03 = data.pressao_c03();
        this.pressao_saida = data.pressao_saida();
        this.houve_troca_carvao = data.houve_troca_carvao();
        this.houve_retrolavagem = data.houve_retrolavagem();
    }
}
