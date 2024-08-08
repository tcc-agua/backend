package com.wise.forms_coleta.dtos.colunas_carvao;


import jakarta.validation.constraints.PositiveOrZero;

public record ColunasCarvaoPutDTO(
        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double presssao_c01,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao_c02,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao_c03,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double pressao_saida,

        Boolean houve_troca_carvao,
        Boolean houve_retrolavagem) {
}
