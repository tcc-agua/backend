package com.wise.forms_coleta.dtos.horimetro;

import jakarta.validation.constraints.NotBlank;

public record HorimetroPutDTO(@NotBlank (message = "O campo 'horimetro' não pode estar em branco!")
                              Double horimetro) {
}
