package com.wise.forms_coleta.dtos.hidrometro;

import jakarta.validation.constraints.NotBlank;

public record HidrometroPutDTO(
        @NotBlank (message = "O campo 'volume' não pode estar em branco!")
        Double volume
) {
}
