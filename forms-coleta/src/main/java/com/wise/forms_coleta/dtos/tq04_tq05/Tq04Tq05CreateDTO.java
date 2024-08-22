package com.wise.forms_coleta.dtos.tq04_tq05;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record Tq04Tq05CreateDTO(

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomePonto,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomeTecnico,

        Boolean houve_preparo_solucao,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Integer qtd_bombonas,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double kg_bombonas,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double horimetro,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double hidrometro
){
}
