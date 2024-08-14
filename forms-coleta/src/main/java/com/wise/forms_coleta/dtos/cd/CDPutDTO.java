package com.wise.forms_coleta.dtos.cd;

import jakarta.validation.constraints.PositiveOrZero;

public record CDPutDTO(
        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        String tipo_rede,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer hidrometro
) {
}
