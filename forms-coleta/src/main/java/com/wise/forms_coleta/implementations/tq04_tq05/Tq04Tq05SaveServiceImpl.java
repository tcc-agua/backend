package com.wise.forms_coleta.implementations.tq04_tq05;

import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05CreateDTO;
import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05DTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.entities.Tq04Tq05;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.repositories.Tq04Tq05Repository;
import com.wise.forms_coleta.services.tq04_tq05.Tq04Tq05SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class Tq04Tq05SaveServiceImpl implements Tq04Tq05SaveService {

    @Autowired
    private PontoRepository pontoRepository;
    @Autowired
    private ColetaRepository coletaRepository;
    @Autowired
    private Tq04Tq05Repository tq04Tq05Repository;

    @Override
    public Tq04Tq05DTO save(Tq04Tq05CreateDTO data) {

        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        Tq04Tq05 tq04Tq05 = new Tq04Tq05(data);
        tq04Tq05.setPonto(ponto);

        coleta.getTq04Tq05Set().add(tq04Tq05);

        coleta.setHora_fim(LocalTime.now());

        tq04Tq05Repository.save(tq04Tq05);
        coletaRepository.save(coleta);


        return new Tq04Tq05DTO(tq04Tq05);
    }
}
