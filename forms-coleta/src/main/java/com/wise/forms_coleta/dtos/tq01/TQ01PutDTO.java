package com.wise.forms_coleta.dtos.tq01;

import jakarta.validation.constraints.PositiveOrZero;

public record TQ01PutDTO(

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double nivel
) {
}
