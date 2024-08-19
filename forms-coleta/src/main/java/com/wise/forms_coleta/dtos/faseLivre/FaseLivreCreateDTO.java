package com.wise.forms_coleta.dtos.faseLivre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record FaseLivreCreateDTO(
        @NotBlank(message = "O campo 'nomePonto' não pode estar em branco!")
        String nomePonto,

        @PositiveOrZero(message = "O campo 'volume' precisa ser pelo menos 0!")
        Double volume,

        Boolean houve_troca,

        @NotBlank(message = "O campo 'nomeTecnico' não pode estar em branco!")
        String nomeTecnico



){
}
