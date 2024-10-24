package com.wise.forms_coleta.dtos.bc01;

import jakarta.validation.constraints.PositiveOrZero;

public record BC01PutDTO(
        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer horimetro,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer frequencia,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double vazao,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer volume
) {
}
