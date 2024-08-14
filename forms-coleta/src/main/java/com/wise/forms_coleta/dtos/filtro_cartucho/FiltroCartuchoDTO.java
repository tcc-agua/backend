package com.wise.forms_coleta.dtos.filtro_cartucho;

import com.wise.forms_coleta.entities.FiltroCartucho;
import com.wise.forms_coleta.entities.Ponto;
import jakarta.validation.constraints.NotBlank;

public record FiltroCartuchoDTO(Long id, Double pressao_entrada, Double pressao_saida, Ponto ponto) {
    public FiltroCartuchoDTO(FiltroCartucho filtroCartucho){
        this(filtroCartucho.getId(), filtroCartucho.getPressao_entrada(), filtroCartucho.getPressao_saida(), filtroCartucho.getPonto());
    }
}
