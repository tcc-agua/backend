package com.wise.forms_coleta.implementations.pmpt;

import com.wise.forms_coleta.dtos.PmPt.PmPtCreateDTO;
import com.wise.forms_coleta.dtos.PmPt.PmPtDTO;
import com.wise.forms_coleta.entities.PmPt;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.PmPtRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.pmpt.PmPtSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PmPtSaveServiceImpl implements PmPtSaveService {

    @Autowired
    private PmPtRepository repository;

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public PmPtDTO save(PmPtCreateDTO data) {

        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado"));

        PmPt pmPt = new PmPt(data);
        pmPt.setPonto(ponto);

        repository.save(pmPt);

        return new PmPtDTO(pmPt);
    }
}
