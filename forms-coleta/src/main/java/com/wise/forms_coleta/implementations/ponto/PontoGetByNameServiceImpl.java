package com.wise.forms_coleta.implementations.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoDTO;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.ponto.PontoGetByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PontoGetByNameServiceImpl implements PontoGetByNameService {
    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public PontoDTO getPointByName(String name) {
        Ponto ponto = pontoRepository.findByNome(name)
                .orElseThrow(() -> new GenericsNotFoundException( "Ponto não encontrado!"));

        return new PontoDTO(ponto);

    }
}
