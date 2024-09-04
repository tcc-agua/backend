package com.wise.forms_coleta.services.Notificacoes;

import com.wise.forms_coleta.dtos.notificacao.NotifCreateDTO;
import com.wise.forms_coleta.dtos.notificacao.NotifDTO;
import org.springframework.stereotype.Service;


public interface NotifSaveService {
    NotifDTO save(NotifCreateDTO data);
}
