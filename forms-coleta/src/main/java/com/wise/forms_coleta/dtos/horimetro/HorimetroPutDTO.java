package com.wise.forms_coleta.dtos.horimetro;

import jakarta.validation.constraints.NotBlank;

public record HorimetroPutDTO(@NotBlank String horimetro) {
}
