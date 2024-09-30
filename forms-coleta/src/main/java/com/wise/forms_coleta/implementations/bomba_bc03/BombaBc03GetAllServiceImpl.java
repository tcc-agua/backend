package com.wise.forms_coleta.implementations.bomba_bc03;

import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03DTO;
import com.wise.forms_coleta.repositories.BombaBc03Repository;
import com.wise.forms_coleta.services.bomba_bc03.BombaBc03GetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BombaBc03GetAllServiceImpl implements BombaBc03GetAllService {
    @Autowired
    private BombaBc03Repository bc03Repository;


    @Override
    public List<BombaBc03DTO> getAll() {
        return bc03Repository.findAll().stream().map(BombaBc03DTO::new).toList();
    }
}
