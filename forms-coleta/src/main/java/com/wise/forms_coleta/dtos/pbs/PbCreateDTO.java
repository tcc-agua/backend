package com.wise.forms_coleta.dtos.pbs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;


public record PbCreateDTO(
        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pulsos,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double nivel_oleo,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double nivel_agua,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double vol_rem_oleo,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomePonto,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomeTecnico,
        Long idColeta
) {
}
