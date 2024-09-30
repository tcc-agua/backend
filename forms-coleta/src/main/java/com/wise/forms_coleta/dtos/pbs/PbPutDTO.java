package com.wise.forms_coleta.dtos.pbs;

import jakarta.validation.constraints.PositiveOrZero;

public record PbPutDTO(@PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                       Double pressao,

                       @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                       Double pulsos,

                       @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                       Double nivel_oleo,

                       @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                       Double nivel_agua,

                       @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                       Double vol_rem_oleo) {
}
