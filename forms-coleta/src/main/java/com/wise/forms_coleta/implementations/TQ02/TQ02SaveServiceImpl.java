package com.wise.forms_coleta.implementations.TQ02;

import com.wise.forms_coleta.dtos.TQ02.TQ02CreateDTO;
import com.wise.forms_coleta.dtos.TQ02.TQ02DTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.entities.TQ02;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.repositories.TQ02Repository;
import com.wise.forms_coleta.services.tq02.TQ02SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class TQ02SaveServiceImpl implements TQ02SaveService {
    @Autowired
    private TQ02Repository tq02Repository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public TQ02DTO save(TQ02CreateDTO data) {
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        Coleta coleta = new Coleta(data.nomeTecnico(), LocalDate.now(), LocalTime.now());

        TQ02 tq02 = new TQ02(data);

        tq02.setPonto(ponto);

        coleta.getTq02Set().add(tq02);
        coleta.setHora_fim(LocalTime.now());

        tq02Repository.save(tq02);
        coletaRepository.save(coleta);

        return new TQ02DTO(tq02);

    }
}
