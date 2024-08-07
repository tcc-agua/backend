package com.wise.forms_coleta.dtos.bc01;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record BC01CreateDTO(@NotBlank String nomePonto, @PositiveOrZero int horimetro, @PositiveOrZero double pressao, @PositiveOrZero int frequencia, @PositiveOrZero double vazao, @PositiveOrZero int volume) {

}
