package com.wise.forms_coleta.implementations.CD;

import com.wise.forms_coleta.dtos.cd.CDDTO;
import com.wise.forms_coleta.repositories.CDRepository;
import com.wise.forms_coleta.services.CD.CDGetAllService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CDGetAllServiceImpl implements CDGetAllService {
    @Autowired
    private CDRepository cdRepo;

    @Override
    public List<CDDTO> getAll() {
        return  cdRepo.findAll().stream().map(CDDTO::new).toList();
    }
 }
