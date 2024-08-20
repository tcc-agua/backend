package com.wise.forms_coleta.implementations.BH02;

import com.wise.forms_coleta.dtos.bh02.BH02DTO;
import com.wise.forms_coleta.dtos.bh02.BH02PutDTO;
import com.wise.forms_coleta.entities.BH02;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BH02Repository;
import com.wise.forms_coleta.services.BH02.BH02PutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BH02PutServiceImpl implements BH02PutService {
    @Autowired
    private BH02Repository bh02Repository;

    @Override
    public BH02DTO put(Long id, BH02DTO data) {
        return null;
    }

    @Override
    public BH02DTO put(Long id, BH02PutDTO data) {
        BH02 bh02 = bh02Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        bh02.setHorimetro(data.horimetro());
        bh02.setFrequencia(data.frequencia());
        bh02.setPressao(data.pressao());

        bh02Repository.save(bh02);
        return new BH02DTO(bh02);
    }

}
