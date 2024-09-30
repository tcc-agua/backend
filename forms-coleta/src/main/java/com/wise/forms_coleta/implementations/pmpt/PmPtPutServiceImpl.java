package com.wise.forms_coleta.implementations.pmpt;

import com.wise.forms_coleta.dtos.PmPt.PmPtDTO;
import com.wise.forms_coleta.dtos.PmPt.PmPtPutDTO;
import com.wise.forms_coleta.entities.PmPt;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.PmPtRepository;
import com.wise.forms_coleta.services.pmpt.PmPtPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PmPtPutServiceImpl implements PmPtPutService {
    @Autowired
    private PmPtRepository pmPtRepository;

    @Override
    public PmPtDTO put(Long id, PmPtPutDTO data) {
        PmPt pmPt = pmPtRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        pmPt.setFl_remo_manual(data.flRemoManual());
        pmPt.setNivel_agua(data.nivelAgua());
        pmPt.setNivel_oleo(data.nivelOleo());

        pmPtRepository.save(pmPt);
        return new PmPtDTO(pmPt);
    }
}
