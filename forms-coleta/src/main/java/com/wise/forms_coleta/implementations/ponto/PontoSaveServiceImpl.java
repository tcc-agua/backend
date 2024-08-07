package com.wise.forms_coleta.implementations.ponto;

import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.ponto.PontoSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PontoSaveServiceImpl implements PontoSaveService {

    @Autowired
    private PontoRepository repository;

    // métodos

    @Override
    public String save(Long id) {
        return "";
    }
}
