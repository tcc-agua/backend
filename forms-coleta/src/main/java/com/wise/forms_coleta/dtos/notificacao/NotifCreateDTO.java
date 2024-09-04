package com.wise.forms_coleta.dtos.notificacao;

import com.wise.forms_coleta.entities.enums.NotifEnum;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record NotifCreateDTO(
        @NotBlank(message = "O campo 'tabela' não pode estar em branco.")
        String tabela,

        NotifEnum tipo

) {
}
