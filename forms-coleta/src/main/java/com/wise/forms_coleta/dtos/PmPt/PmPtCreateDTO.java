package com.wise.forms_coleta.dtos.PmPt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record PmPtCreateDTO(

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double nivelAgua,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double nivelOleo,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double flRemoManual,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomePonto,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomeTecnico
){
}
