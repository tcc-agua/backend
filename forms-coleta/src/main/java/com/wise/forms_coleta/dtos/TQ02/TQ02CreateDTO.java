package com.wise.forms_coleta.dtos.TQ02;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record TQ02CreateDTO(

        @PositiveOrZero
        Double sensor_ph,

        @NotNull
        String it_02_1,

        @NotNull
        String nomePonto,

        @NotNull
        String nomeTecnico

) {
}
