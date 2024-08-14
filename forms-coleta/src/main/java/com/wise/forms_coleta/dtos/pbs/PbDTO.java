package com.wise.forms_coleta.dtos.pbs;

import jakarta.validation.constraints.PositiveOrZero;

public record PbDTO(
        @PositiveOrZero
        Double pressao,

        @PositiveOrZero
        Double pulsos,

        @PositiveOrZero
        Double nivel_oleo,

        @PositiveOrZero
        Double nivel_agua,

        @PositiveOrZero
        Double vol_rem_oleo
) {
}
