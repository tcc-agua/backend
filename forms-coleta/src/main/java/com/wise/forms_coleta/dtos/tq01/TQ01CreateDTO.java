package com.wise.forms_coleta.dtos.tq01;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record TQ01CreateDTO(

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a zero!")
        String nivel,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomePonto,

        Long idColeta

) {
}
