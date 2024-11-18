package com.wise.forms_coleta.implementations.Notificacoes;

import com.wise.forms_coleta.entities.Horimetro;
import com.wise.forms_coleta.entities.Notificacoes;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.NotifRepository;
import com.wise.forms_coleta.services.Notificacoes.NotifDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifDeleteServiceImpl implements NotifDeleteService {

    @Autowired
    private NotifRepository notifRepository;

    // Método para deletar uma instância de notificação
    @Override
    public String delete(Long id) {
        // Pegando a instância de notificação pelo id
        Notificacoes notificacoes = notifRepository.findById(id)
                .orElseThrow(()-> new GenericsNotFoundException("Formulário não encontrado!"));
        // Deletando no banco a instância
        notifRepository.delete(notificacoes);
        return "Formulário deletado com sucesso!";
    }
}
