package com.wise.forms_coleta.implementations.pmpt;

import com.wise.forms_coleta.dtos.PmPt.PmPtCreateDTO;
import com.wise.forms_coleta.dtos.PmPt.PmPtDTO;
import com.wise.forms_coleta.entities.PmPt;
import com.wise.forms_coleta.repositories.PmPtRepository;
import com.wise.forms_coleta.services.pmpt.PmPtSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PmPtSaveServiceImpl implements PmPtSaveService {

    @Autowired
    private PmPtRepository repository;

    @Override
    public PmPtDTO save(PmPtCreateDTO data) {
        PmPt pmPt = new PmPt(data);

        return new PmPtDTO(repository.save(pmPt));
    }
}
