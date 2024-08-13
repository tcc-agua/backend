package com.wise.forms_coleta.implementations.BC01;

import com.wise.forms_coleta.dtos.bc01.BC01DTO;
import com.wise.forms_coleta.repositories.BC01Repository;
import com.wise.forms_coleta.services.BC01.BC01GetAllService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BC01GetAllServiceImpl implements BC01GetAllService {

    @Autowired
    private BC01Repository bc01repo;

    @Override
    public List<BC01DTO> getAll() {
        return bc01repo.findAll().stream().map(BC01DTO::new).toList();
    }
}
