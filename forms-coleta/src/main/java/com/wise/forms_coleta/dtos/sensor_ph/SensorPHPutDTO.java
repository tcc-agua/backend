package com.wise.forms_coleta.dtos.sensor_ph;

import jakarta.validation.constraints.PositiveOrZero;

public record SensorPHPutDTO(

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double ph

) {
}
