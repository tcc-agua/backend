package com.wise.forms_coleta.implementations.BC06;

import com.wise.forms_coleta.dtos.BC06.BC06DTO;
import com.wise.forms_coleta.repositories.BC06Repository;
import com.wise.forms_coleta.services.BC06.BC06GetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BC06GetAllServiceImpl implements BC06GetAllService {
    @Autowired
    private BC06Repository bc06Repository;

    @Override
    public List<BC06DTO> getAll() {
        return bc06Repository.findAll().stream().map(BC06DTO::new).toList();
    }
}
