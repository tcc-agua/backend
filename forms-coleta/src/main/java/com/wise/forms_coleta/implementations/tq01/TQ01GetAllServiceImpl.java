package com.wise.forms_coleta.implementations.tq01;

import com.wise.forms_coleta.dtos.tq01.TQ01DTO;
import com.wise.forms_coleta.repositories.TQ01Repository;
import com.wise.forms_coleta.services.tq01.TQ01GetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TQ01GetAllServiceImpl implements TQ01GetAllService {

    @Autowired
    private TQ01Repository tq01Repository;

    @Override
    public List<TQ01DTO> getAll() {
        return tq01Repository.findAll().stream().map(TQ01DTO::new).toList();
    }
}
