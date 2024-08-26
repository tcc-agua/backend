package com.wise.forms_coleta.dtos.faseLivre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record FaseLivreCreateDTO(
        @NotBlank(message = "O campo não pode estar em branco!")
        String nomePonto,

        @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
        Double volume,

        Boolean houve_troca,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nomeTecnico,
        Long idColeta

){
}
