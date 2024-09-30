package com.wise.forms_coleta.implementations.TQ02;

import com.wise.forms_coleta.dtos.TQ02.TQ02DTO;
import com.wise.forms_coleta.repositories.TQ02Repository;
import com.wise.forms_coleta.services.tq02.Tq02GetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TQ02GetAllServiceImpl implements Tq02GetAllService {
    @Autowired
    private TQ02Repository tq02Repository;

    @Override
    public List<TQ02DTO> getAll() {
        return tq02Repository.findAll().stream().map(TQ02DTO::new).toList();
    }
}
