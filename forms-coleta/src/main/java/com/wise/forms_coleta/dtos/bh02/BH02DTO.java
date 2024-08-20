package com.wise.forms_coleta.dtos.bh02;

import com.wise.forms_coleta.entities.BH02;
import com.wise.forms_coleta.entities.Ponto;

public record BH02DTO(
        Long id,
        Integer horimetro,
        Integer pressao,
        Integer frequencia,
        Ponto ponto
) {
    public BH02DTO(BH02 data) {
        this(data.getId(), data.getHorimetro(), data.getPressao(), data.getFrequencia(), data.getPonto());
    }
}
