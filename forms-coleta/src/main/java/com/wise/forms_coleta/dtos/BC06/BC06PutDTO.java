package com.wise.forms_coleta.dtos.BC06;

import jakarta.validation.constraints.NotBlank;

public record BC06PutDTO(@NotBlank String pressao
        , @NotBlank String horimetro) {
}
