package com.wise.forms_coleta.implementations.CD;

import com.wise.forms_coleta.dtos.cd.CDCreateDTO;
import com.wise.forms_coleta.dtos.cd.CDDTO;
import com.wise.forms_coleta.entities.CD;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.CDRepository;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.CD.CDSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class CDSaveServiceImpl implements CDSaveService {
    @Autowired
    private CDRepository cdRepo;

    @Autowired
    private ColetaRepository coletaRepository;

    @Autowired
    private PontoRepository pontoRepository;

    // Método de salvar nova coleta de CD
    @Override
    public CDDTO save(CDCreateDTO data) {
        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade CD
        CD cd = new CD(data);
        // Setando o ponto
        cd.setPonto(ponto);
        // Associando a instância de CD criada a coleta
        coleta.getCdSet().add(cd);

        // Salvando no banco a instância de CD
        cdRepo.save(cd);
        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return  new CDDTO(cd);
    }
}
