package com.wise.forms_coleta.implementations.BS01Hidrometro;

import com.wise.forms_coleta.dtos.BS01Hidrometro.BS01HidrometroDTO;
import com.wise.forms_coleta.repositories.BS01HidrometroRepository;
import com.wise.forms_coleta.services.BS01Hidrometro.BS01HidrometroGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BS01HidrometroGetAllServiceImpl implements BS01HidrometroGetAllService {

    @Autowired
    private BS01HidrometroRepository bs01HidrometroRepository;

    @Override
    public List<BS01HidrometroDTO> getAll() {
        return bs01HidrometroRepository.findAll().stream().map(BS01HidrometroDTO::new).toList();
    }

}
