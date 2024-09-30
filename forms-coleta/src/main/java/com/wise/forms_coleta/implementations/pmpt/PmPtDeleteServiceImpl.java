package com.wise.forms_coleta.implementations.pmpt;

import com.wise.forms_coleta.entities.PmPt;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.PmPtRepository;
import com.wise.forms_coleta.services.pmpt.PmPtDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PmPtDeleteServiceImpl implements PmPtDeleteService {
    @Autowired
    private PmPtRepository pmPtRepository;

    @Override
    public String delete(Long id) {
        PmPt pmPt = pmPtRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));
        pmPtRepository.delete(pmPt);
        return "Formulário deletado com sucesso!";
    }
}
