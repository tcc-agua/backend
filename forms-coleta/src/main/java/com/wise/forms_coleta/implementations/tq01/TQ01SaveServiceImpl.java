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

    // Método de salvar nova coleta de TQ01
    @Override
    public TQ01DTO save(TQ01CreateDTO data) {

        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto =pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade TQ01
        TQ01 tq01 = new TQ01(data);
        // Setando o ponto
        tq01.setPonto(ponto);
        // Associando a instância de TQ01 criada a coleta
        coleta.getTq01Set().add(tq01);

        // Salvando no banco a instância de TQ01
        tq01Repository.save(tq01);
        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new TQ01DTO(tq01);




    }
}
