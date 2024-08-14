package com.wise.forms_coleta.entities;

import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Ponto ponto;

    public FiltroCartucho(FiltroCartuchoCreateDTO data){
        this.pressao_entrada = data.pressao_entrada();
        this.pressao_saida = data.pressao_saida();
    }
}
