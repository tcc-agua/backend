package com.wise.forms_coleta.dtos.sensor_ph;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record SensorPHCreateDTO(

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double ph,

        @NotBlank(message = "O campo 'nomePonto' não pode estar em branco!")
        String nomePonto,

        @NotBlank
        String nomeTecnico

) {



}


