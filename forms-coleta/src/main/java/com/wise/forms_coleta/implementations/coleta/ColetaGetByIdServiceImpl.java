package com.wise.forms_coleta.implementations.coleta;

import com.wise.forms_coleta.dtos.coleta.ColetaDTO;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.services.coleta.ColetaGetByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColetaGetByIdServiceImpl implements ColetaGetByIdService {
    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public ColetaDTO getById(Long id) {
        return new ColetaDTO(coletaRepository.findById(id).orElseThrow(() -> new GenericsNotFoundException("Coleta não encontrada!")));
    }
}
