package com.wise.forms_coleta.dtos.horimetro;

import jakarta.validation.constraints.NotBlank;

public record HorimetroCreateDTO(
        @NotBlank (message = "O campo 'horimetro' não pode estar em branco!")
        String horimetro,
        @NotBlank(message = "O campo 'nomePonto' não pode estar em branco!")
        String nomePonto,
        @NotBlank(message = "O campo 'nomeTecnico' não pode estar em branco!")
        String nomeTecnico
) {
}
