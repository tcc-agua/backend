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

    // Método de salvar nova coleta de PmPt
    @Override
    public PmPtDTO save(PmPtCreateDTO data) {

        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade PmPt
        PmPt pmPt = new PmPt(data);
        // Setando o ponto
        pmPt.setPonto(ponto);
        // Associando a instância de PmPt criada a coleta
        coleta.getPmPtSet().add(pmPt);

        // Salvando no banco a instância de PmPt
        pmPtrepository.save(pmPt);
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);
        return new PmPtDTO(pmPt);
    }
}
