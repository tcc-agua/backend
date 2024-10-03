package com.wise.forms_coleta.dtos.hidrometro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record HidrometroPutDTO(
        @PositiveOrZero(message = "O campo 'volume' não pode estar em branco!")
        Double volume
) {
}
