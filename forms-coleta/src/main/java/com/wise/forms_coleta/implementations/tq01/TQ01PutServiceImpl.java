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

    @Override
    public TQ01DTO put(Long id, TQ01PutDTO data) {
        TQ01 tq01 = tq01Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        tq01.setNivel(data.nivel());

        tq01Repository.save(tq01);
        return new TQ01DTO(tq01);
    }
}
