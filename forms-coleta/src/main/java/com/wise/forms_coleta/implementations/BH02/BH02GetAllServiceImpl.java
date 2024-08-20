package com.wise.forms_coleta.implementations.BH02;

import com.wise.forms_coleta.dtos.bh02.BH02DTO;
import com.wise.forms_coleta.repositories.BH02Repository;
import com.wise.forms_coleta.services.BH02.BH02GetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BH02GetAllServiceImpl implements BH02GetAllService {
    @Autowired
    private BH02Repository bh02Repository;

    @Override
    public List<BH02DTO> getAll() {
        return bh02Repository.findAll().stream().map(BH02DTO::new).toList();
    }
}
