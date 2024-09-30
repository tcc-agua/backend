package com.wise.forms_coleta.dtos.PmPt;

import jakarta.validation.constraints.PositiveOrZero;

public record PmPtPutDTO(@PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                         Double nivelAgua,

                         @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                         Double nivelOleo,

                         @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                         Double flRemoManual) {
}
