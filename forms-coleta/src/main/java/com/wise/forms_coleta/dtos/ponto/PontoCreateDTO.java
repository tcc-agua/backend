package com.wise.forms_coleta.dtos.ponto;

import jakarta.validation.constraints.NotBlank;

public record PontoCreateDTO(@NotBlank String nome, @NotBlank String localizacao, @NotBlank String excel) {
}
