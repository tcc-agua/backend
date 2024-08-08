package com.wise.forms_coleta.dtos.ponto;

import com.wise.forms_coleta.entities.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;

public record PontoPutDTO(
        @NotBlank(message = "O campo 'nome' não pode estar em branco!")
        String nome,

        @NotBlank(message = "O campo 'localização' não pode estar em branco!")
        String localizacao,

        @NotBlank(message = "O campo 'excel' não pode estar em branco!")
        String excel,

        StatusEnum statusEnum
) {
}
