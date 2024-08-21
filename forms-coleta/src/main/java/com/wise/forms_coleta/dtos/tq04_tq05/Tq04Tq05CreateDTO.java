package com.wise.forms_coleta.dtos.tq04_tq05;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record Tq04Tq05CreateDTO(

        @NotBlank(message = "O campo 'nomePonto' não pode estar em branco!")
        String nomePonto,

        @NotBlank(message = "O campo 'nomeTecnico' não pode estar em branco!")
        String nomeTecnico,

        Boolean houve_preparo_solucao,

        @PositiveOrZero
        Integer qtd_bombonas,

        @PositiveOrZero
        Double kg_bombonas,

        @PositiveOrZero
        Double horimetro,

        @PositiveOrZero
        Double hidrometro
){
}
