package com.wise.forms_coleta.dtos.TQ02;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record TQ02CreateDTO(

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a zero!")
        Double sensor_ph,

        @NotNull(message = "O campo não pode estar vazio!")
        String Lt_02_1,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomePonto,

        Long idColeta

) {
}
