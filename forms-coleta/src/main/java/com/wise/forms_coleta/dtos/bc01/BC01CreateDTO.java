package com.wise.forms_coleta.dtos.bc01;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record BC01CreateDTO(
        @NotBlank(message = "O campo 'nomePonto' não pode estar em branco!")
        String nomePonto,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        int horimetro,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        double pressao,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        int frequencia,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        double vazao,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        int volume
){

}
