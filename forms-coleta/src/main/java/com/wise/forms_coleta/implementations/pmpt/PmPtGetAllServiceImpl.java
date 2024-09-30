package com.wise.forms_coleta.implementations.pmpt;

import com.wise.forms_coleta.dtos.PmPt.PmPtDTO;
import com.wise.forms_coleta.repositories.PmPtRepository;
import com.wise.forms_coleta.services.pmpt.PmPtGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PmPtGetAllServiceImpl implements PmPtGetAllService {
    @Autowired
    private PmPtRepository pmPtRepository;

    @Override
    public List<PmPtDTO> getAll() {
        return pmPtRepository.findAll().stream().map(PmPtDTO::new).toList();
    }
}
