package com.wise.forms_coleta.dtos.BS01Pressao;

import jakarta.validation.constraints.PositiveOrZero;

public record BS01PressaoPutDTO(
        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao
) {
}
