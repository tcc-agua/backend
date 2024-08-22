package com.wise.forms_coleta.dtos.ponto;

import jakarta.validation.constraints.NotBlank;

public record PontoCreateDTO(
        @NotBlank(message = "O campo não pode estar em branco!")
        String nome,

        @NotBlank(message = "O campo não pode estar em branco!")
        String localizacao,

        @NotBlank(message = "O campo não pode estar em branco!")
        String nome_excel
) {
}
