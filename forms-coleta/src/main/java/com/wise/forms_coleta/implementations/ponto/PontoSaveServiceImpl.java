package com.wise.forms_coleta.implementations.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoCreateDTO;
import com.wise.forms_coleta.dtos.ponto.PontoDTO;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.ponto.PontoSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PontoSaveServiceImpl implements PontoSaveService {

    @Autowired
    private PontoRepository pontoRepository;

    // métodos

    @Override
    public PontoDTO save(PontoCreateDTO data) {
        Ponto ponto = new Ponto(data);
        pontoRepository.save(ponto);
        return new PontoDTO(ponto);
    }

}
