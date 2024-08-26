package com.wise.forms_coleta.dtos.cd;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record CDCreateDTO(
        @NotBlank(message = "O campo não pode estar em branco!")
        String nomePonto,

        String tipo_rede,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer hidrometro,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomeTecnico,
        Long idColeta

) {
}
