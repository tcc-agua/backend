package com.wise.forms_coleta.implementations.TQ02;

import com.wise.forms_coleta.dtos.TQ02.TQ02DTO;
import com.wise.forms_coleta.dtos.TQ02.TQ02PutDTO;
import com.wise.forms_coleta.entities.TQ02;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.TQ02Repository;
import com.wise.forms_coleta.services.tq02.Tq02PutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TQ02PutServiceImpl implements Tq02PutService {
    @Autowired
    private TQ02Repository tq02Repository;

    @Override
    public TQ02DTO put(Long id, TQ02PutDTO data) {
        TQ02 tq02 = tq02Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        tq02.setSensor_ph(data.sensor_ph());
        tq02.setLt_02_1(data.Lt_02_1());

        tq02Repository.save(tq02);
        return new TQ02DTO(tq02);
    }
}
