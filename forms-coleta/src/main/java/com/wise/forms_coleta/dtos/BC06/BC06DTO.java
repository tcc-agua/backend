package com.wise.forms_coleta.dtos.BC06;

import com.wise.forms_coleta.entities.BC06;
import com.wise.forms_coleta.entities.Ponto;

public record BC06DTO(Long id, Double pressao, Double horimetro, Ponto ponto) {
    public BC06DTO(BC06 bc06){
        this(bc06.getId(), bc06.getPressao(), bc06.getHorimetro(), bc06.getPonto());
    }

}
