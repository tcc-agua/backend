package com.wise.forms_coleta.dtos.sensor_ph;

import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.entities.SensorPH;

public record SensorPHDTO(

        Long id,
        Double ph,
        Ponto ponto

) {
    public SensorPHDTO(SensorPH data) {
        this(data.getId(), data.getPh(), data.getPonto());
    }
}
