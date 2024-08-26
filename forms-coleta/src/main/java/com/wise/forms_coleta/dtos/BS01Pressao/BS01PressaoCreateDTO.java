package com.wise.forms_coleta.dtos.BS01Pressao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record BS01PressaoCreateDTO(
        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomePonto,

        Long idColeta
){}
