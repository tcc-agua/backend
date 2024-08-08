package com.wise.forms_coleta.implementations.coleta;

import com.wise.forms_coleta.dtos.coleta.ColetaCreateDTO;
import com.wise.forms_coleta.dtos.coleta.ColetaDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.services.coleta.ColetaSaveService;
import org.springframework.beans.factory.annotation.Autowired;

public class ColetaSaveServiceImpl implements ColetaSaveService {
    @Autowired
    ColetaRepository coletaRepository;

    @Override
    public ColetaDTO save(ColetaCreateDTO data) {
        Coleta coleta = new Coleta(data);
        coletaRepository.save(coleta);
        return new ColetaDTO(coleta);
    }
}
