package com.wise.forms_coleta.dtos.tq01;

import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.entities.TQ01;

public record TQ01DTO(
        Long id,
        Double nivel,
        Ponto ponto
) {
    public TQ01DTO(TQ01 data) {
        this(data.getId(), data.getNivel(), data.getPonto());
    }
}
