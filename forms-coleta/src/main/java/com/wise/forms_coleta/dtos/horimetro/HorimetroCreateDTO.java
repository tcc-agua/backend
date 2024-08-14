package com.wise.forms_coleta.dtos.horimetro;

import jakarta.validation.constraints.NotBlank;

public record HorimetroCreateDTO(
        @NotBlank String horimetro,
        @NotBlank String nomePonto
) {
}
