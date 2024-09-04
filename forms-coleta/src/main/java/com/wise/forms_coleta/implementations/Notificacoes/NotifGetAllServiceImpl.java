package com.wise.forms_coleta.implementations.Notificacoes;

import com.wise.forms_coleta.dtos.notificacao.NotifDTO;
import com.wise.forms_coleta.repositories.NotifRepository;
import com.wise.forms_coleta.services.Notificacoes.NotifGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotifGetAllServiceImpl implements NotifGetAllService {

    @Autowired
    private NotifRepository notifRepository;

    @Override
    public List<NotifDTO> getAll() {
        return notifRepository.findAll().stream().map(NotifDTO::new).toList();
    }
}
