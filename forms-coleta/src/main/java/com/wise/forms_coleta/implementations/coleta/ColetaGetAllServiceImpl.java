package com.wise.forms_coleta.implementations.coleta;

import com.wise.forms_coleta.dtos.coleta.ColetaDTO;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.services.coleta.ColetaGetAllService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ColetaGetAllServiceImpl implements ColetaGetAllService {

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public List<ColetaDTO> getAll() {
        return coletaRepository.findAll().stream().map(ColetaDTO::new).toList();
    }
}
