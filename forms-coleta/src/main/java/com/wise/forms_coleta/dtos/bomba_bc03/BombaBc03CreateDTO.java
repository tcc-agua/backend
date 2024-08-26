package com.wise.forms_coleta.dtos.bomba_bc03;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record BombaBc03CreateDTO(
        @NotBlank(message = "O campo não pode estar em branco!")
        String nomePonto,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double hidrometro,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double horimetro,
        Long idColeta

) {
}
