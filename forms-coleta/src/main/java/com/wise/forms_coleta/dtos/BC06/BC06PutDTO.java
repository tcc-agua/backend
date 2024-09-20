package com.wise.forms_coleta.dtos.BC06;

import jakarta.validation.constraints.NotBlank;

public record BC06PutDTO(
        @NotBlank Double pressao,
        @NotBlank Double horimetro
) {
}
