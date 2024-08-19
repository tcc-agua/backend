package com.wise.forms_coleta.implementations.tq01;

import com.wise.forms_coleta.dtos.tq01.TQ01CreateDTO;
import com.wise.forms_coleta.dtos.tq01.TQ01DTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.entities.TQ01;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.repositories.TQ01Repository;
import com.wise.forms_coleta.services.tq01.TQ01SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class TQ01SaveServiceImpl implements TQ01SaveService {

    @Autowired
    TQ01Repository tq01Repository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public TQ01DTO save(TQ01CreateDTO data) {
        Ponto ponto =pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        Coleta coleta = new Coleta(data.nomeTecnico(), LocalDate.now(), LocalTime.now());

        TQ01 tq01 = new TQ01(data);
        tq01.setPonto(ponto);

        coleta.getTq01Set().add(tq01);

        tq01Repository.save(tq01);
        coleta.setHora_fim(LocalTime.now());
        coletaRepository.save(coleta);

        return new TQ01DTO(tq01);




    }
}
