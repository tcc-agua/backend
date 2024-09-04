package com.wise.forms_coleta.services.Notificacoes;

import com.wise.forms_coleta.dtos.notificacao.NotifDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NotifGetAllService {
    List<NotifDTO> getAll();
}
