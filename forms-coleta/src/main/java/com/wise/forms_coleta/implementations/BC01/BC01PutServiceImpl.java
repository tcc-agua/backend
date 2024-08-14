package com.wise.forms_coleta.implementations.BC01;

import com.wise.forms_coleta.dtos.bc01.BC01DTO;
import com.wise.forms_coleta.dtos.bc01.BC01PutDTO;
import com.wise.forms_coleta.entities.BC01;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BC01Repository;
import com.wise.forms_coleta.services.BC01.BC01PutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BC01PutServiceImpl implements BC01PutService {

    @Autowired
    private BC01Repository bc01repo;


    @Override
    public BC01DTO put(Long id, BC01PutDTO data) {
        BC01 bc01 = bc01repo.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        bc01.setHorimetro(data.horimetro());
        bc01.setPressao(data.pressao());
        bc01.setFrequencia(data.frequencia());
        bc01.setVazao(data.vazao());
        bc01.setVolume(data.volume());

        bc01repo.save(bc01);
        return new BC01DTO(bc01);
    }
}
