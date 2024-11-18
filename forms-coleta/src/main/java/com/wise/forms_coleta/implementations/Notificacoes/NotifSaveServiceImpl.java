package com.wise.forms_coleta.implementations.Notificacoes;

import com.wise.forms_coleta.dtos.notificacao.NotifCreateDTO;
import com.wise.forms_coleta.dtos.notificacao.NotifDTO;
import com.wise.forms_coleta.entities.Notificacoes;
import com.wise.forms_coleta.repositories.NotifRepository;
import com.wise.forms_coleta.services.Notificacoes.NotifSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifSaveServiceImpl implements NotifSaveService {

    @Autowired
    NotifRepository notifRepository;

    // Retornando um NotifDTO e dentro dele salvando no banco uma nova instância de Notificação
    @Override
    public NotifDTO save(NotifCreateDTO data) {
        return new NotifDTO(notifRepository.save(new Notificacoes(data)));
    }

}
