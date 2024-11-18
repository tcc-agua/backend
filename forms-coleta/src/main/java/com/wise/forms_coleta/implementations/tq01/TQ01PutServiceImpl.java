package com.wise.forms_coleta.implementations.tq01;

import com.wise.forms_coleta.dtos.tq01.TQ01DTO;
import com.wise.forms_coleta.dtos.tq01.TQ01PutDTO;
import com.wise.forms_coleta.entities.TQ01;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.TQ01Repository;
import com.wise.forms_coleta.services.tq01.TQ01PutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TQ01PutServiceImpl implements TQ01PutService {

    @Autowired
    private TQ01Repository tq01Repository;

    // Método de alterar a instância de TQ01
    @Override
    public TQ01DTO put(Long id, TQ01PutDTO data) {
        // Encontrando a instância de TQ01 pelo id
        TQ01 tq01 = tq01Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        // Setando as alterações nos campos
        tq01.setNivel(data.nivel());

        // Salvando no banco as alterações
        tq01Repository.save(tq01);
        return new TQ01DTO(tq01);
    }
}
