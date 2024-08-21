package com.wise.forms_coleta.dtos.bomba_bc03;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record BombaBc03CreateDTO(
        @NotBlank(message = "O campo 'nomePonto' não pode estar em branco!")
        String nomePonto,

        @NotBlank(message = "O campo 'nomeTecnico' não pode estar em branco!")
        String nomeTecnico,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double hidrometro,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double horimetro

) {
}
