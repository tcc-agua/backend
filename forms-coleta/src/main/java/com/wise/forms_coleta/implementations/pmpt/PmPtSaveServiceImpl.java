package com.wise.forms_coleta.implementations.pmpt;

import com.wise.forms_coleta.dtos.PmPt.PmPtCreateDTO;
import com.wise.forms_coleta.dtos.PmPt.PmPtDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.PmPt;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PmPtRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.pmpt.PmPtSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PmPtSaveServiceImpl implements PmPtSaveService {

    @Autowired
    private PmPtRepository pmPtrepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public PmPtDTO save(PmPtCreateDTO data) {

        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado"));

        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        PmPt pmPt = new PmPt(data);
        pmPt.setPonto(ponto);
        coleta.getPmPtSet().add(pmPt);


        pmPtrepository.save(pmPt);
        coletaRepository.save(coleta);
        return new PmPtDTO(pmPt);
    }
}
