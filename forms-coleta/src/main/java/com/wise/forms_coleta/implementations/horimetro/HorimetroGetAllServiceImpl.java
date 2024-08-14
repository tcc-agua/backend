package com.wise.forms_coleta.implementations.horimetro;

import com.wise.forms_coleta.dtos.horimetro.HorimetroDTO;
import com.wise.forms_coleta.repositories.HorimetroRepository;
import com.wise.forms_coleta.services.horimetro.HorimetroGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorimetroGetAllServiceImpl implements HorimetroGetAllService {
    @Autowired
    private HorimetroRepository horimetroRepository;

    @Override
    public List<HorimetroDTO> getAll() {
        return horimetroRepository.findAll().stream().map(HorimetroDTO::new).toList();
    }
}
