package com.wise.forms_coleta.implementations.tq04_tq05;

import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05DTO;
import com.wise.forms_coleta.repositories.Tq04Tq05Repository;
import com.wise.forms_coleta.services.tq04_tq05.Tq04Tq05GetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Tq04Tq05GetAllServiceImpl implements Tq04Tq05GetAllService {
    @Autowired
    private Tq04Tq05Repository tq04Tq05Repository;

    @Override
    public List<Tq04Tq05DTO> getAll() {
        return tq04Tq05Repository.findAll().stream().map(Tq04Tq05DTO::new).toList();
    }
}
