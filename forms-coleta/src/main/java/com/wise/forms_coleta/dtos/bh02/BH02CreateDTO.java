package com.wise.forms_coleta.dtos.bh02;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record BH02CreateDTO(
        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer horimetro,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer pressao,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer frequencia,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomePonto,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomeTecnico

) {
}
