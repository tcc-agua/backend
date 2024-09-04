package com.wise.forms_coleta.implementations.Notificacoes;

import com.wise.forms_coleta.repositories.NotifRepository;
import com.wise.forms_coleta.services.Notificacoes.NotifDeleteAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifDeleteAllServiceImpl implements NotifDeleteAllService {

    @Autowired
    private NotifRepository notifRepository;

    @Override
    public String deleteAll() {
        notifRepository.deleteAll();
        return "Todas as notificações foram deletadas com sucesso!";
    }

}
