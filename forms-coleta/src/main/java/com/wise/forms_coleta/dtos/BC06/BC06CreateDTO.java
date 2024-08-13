package com.wise.forms_coleta.dtos.BC06;

import jakarta.validation.constraints.NotBlank;

public record BC06CreateDTO(@NotBlank(message = "O campo 'pressao' não pode estar em branco!")
                            String pressao,
                            @NotBlank(message = "O campo 'horimetro' não pode estar em branco!")
                            String horimetro,
                            @NotBlank(message = "O campo 'nomePonto' não pode estar em branco!")
                            String nomePonto) {
}
