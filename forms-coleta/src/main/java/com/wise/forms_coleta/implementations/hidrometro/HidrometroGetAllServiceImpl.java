package com.wise.forms_coleta.implementations.hidrometro;

import com.wise.forms_coleta.dtos.hidrometro.HidrometroDTO;
import com.wise.forms_coleta.repositories.HidrometroRepository;
import com.wise.forms_coleta.services.hidrometro.HidrometroGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HidrometroGetAllServiceImpl implements HidrometroGetAllService {

    @Autowired
    private HidrometroRepository hidrometroRepository;

    @Override
    public List<HidrometroDTO> getAll() {
        return hidrometroRepository.findAll().stream().map(HidrometroDTO::new).toList();
    }
}
