package com.wise.forms_coleta.dtos.filtro_cartucho;

import jakarta.validation.constraints.PositiveOrZero;

public record FiltroCartuchoPutDTO(
        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao_entrada,
        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao_saida) {
}