package com.wise.forms_coleta.implementations.pbs;

import com.wise.forms_coleta.dtos.pbs.PbCreateDTO;
import com.wise.forms_coleta.dtos.pbs.PbDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.PBs;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PbRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.pbs.PbSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PbSaveServiceImpl implements PbSaveService {

    @Autowired
    private PbRepository pbRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    // Método de salvar nova coleta de PB
    @Override
    public PbDTO save(PbCreateDTO data) {

        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade PB
        PBs pBs = new PBs(data);
        // Setando o ponto
        pBs.setPonto(ponto);
        // Associando a instância de PB criada a coleta
        coleta.getPbSet().add(pBs);
        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());

        // Salvando no banco a instância de PB
        pbRepository.save(pBs);
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new PbDTO(pBs);
    }
}
