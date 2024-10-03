package com.wise.forms_coleta.dtos.hidrometro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record HidrometroCreateDTO(
        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double volume,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomePonto,

        Long idColeta
) {
}
