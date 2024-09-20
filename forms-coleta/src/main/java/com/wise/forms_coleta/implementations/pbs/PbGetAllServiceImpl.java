package com.wise.forms_coleta.implementations.pbs;

import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.dtos.pbs.PbDTO;
import com.wise.forms_coleta.repositories.PbRepository;
import com.wise.forms_coleta.services.pbs.PbGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PbGetAllServiceImpl implements PbGetAllService {

    @Autowired
    private PbRepository pbRepository;

    @Override
    public List<PbDTO> getAll() {
        return pbRepository.findAll().stream().map(PbDTO::new).toList();
    }
}
