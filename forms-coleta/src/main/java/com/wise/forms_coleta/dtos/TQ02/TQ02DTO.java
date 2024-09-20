package com.wise.forms_coleta.dtos.TQ02;

import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.entities.TQ02;

public record TQ02DTO(
        Long id,
        Double sensor_ph,
        Double Lt_02_1,
        Ponto ponto

) {

    public TQ02DTO(TQ02 tq02){
        this(tq02.getId(), tq02.getSensor_ph(), tq02.getLt_02_1(), tq02.getPonto());
    }
}
