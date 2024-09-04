package com.wise.forms_coleta.dtos.notificacao;

import com.wise.forms_coleta.entities.Notificacoes;
import com.wise.forms_coleta.entities.enums.NotifEnum;

import java.time.LocalDate;

public record NotifDTO(
        Long id,
        String tabela,
        NotifEnum tipo,
        LocalDate data
) {
    public NotifDTO(Notificacoes notificacoes) {
        this(notificacoes.getId(), notificacoes.getTabela(), notificacoes.getTipo(), notificacoes.getData());
    }
}
