package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="filtro_cartucho")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FiltroCartucho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double pressao_entrada;
    private Double pressao_saida;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    @JsonIgnore
    private Ponto ponto;

    @ManyToMany(mappedBy = "filtroCartuchoSet", fetch = FetchType.EAGER)
    private Set<Coleta> coletas = new HashSet<>();

    public FiltroCartucho(FiltroCartuchoCreateDTO data){
        this.pressao_entrada = data.pressao_entrada();
        this.pressao_saida = data.pressao_saida();
    }
}
