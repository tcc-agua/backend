package com.wise.forms_coleta.dtos.BC06;

import com.wise.forms_coleta.entities.BC06;

public record BC06DTO(Long id, String pressao, String horimetro) {
    public BC06DTO(BC06 bc06){
        this(bc06.getId(), bc06.getPressao(), bc06.getHorimetro());
    }

}
