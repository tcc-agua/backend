package com.wise.forms_coleta.dtos.bh02;

import jakarta.validation.constraints.PositiveOrZero;

public record BH02PutDTO(

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer horimetro,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer pressao,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer frequencia

) {
}
