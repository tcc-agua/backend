package com.wise.forms_coleta.dtos.BC06;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record BC06CreateDTO(
        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao,
        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double horimetro,
        @NotBlank(message = "O campo não pode estar em branco!")
        String nomePonto,
        Long idColeta
) {
}
