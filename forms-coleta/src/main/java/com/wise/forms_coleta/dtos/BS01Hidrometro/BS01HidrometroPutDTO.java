package com.wise.forms_coleta.dtos.BS01Hidrometro;

import jakarta.validation.constraints.PositiveOrZero;

public record BS01HidrometroPutDTO(

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer volume

) {
}
