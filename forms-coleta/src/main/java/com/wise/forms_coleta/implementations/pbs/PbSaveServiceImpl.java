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


    @Override
    public PbDTO save(PbCreateDTO data) {

        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado"));

        Coleta coleta = new Coleta(data.nomeTecnico(), LocalDate.now(), LocalTime.now());

        PBs pBs = new PBs(data);
        pBs.setPonto(ponto);

        coleta.getPbSet().add(pBs);
        coleta.setHora_fim(LocalTime.now());

        pbRepository.save(pBs);
        coletaRepository.save(coleta);

        return new PbDTO(pBs);
    }
}
