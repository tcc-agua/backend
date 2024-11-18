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

    // Método de salvar nova coleta de TQ04/TQ05
    @Override
    public Tq04Tq05DTO save(Tq04Tq05CreateDTO data) {

        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade TQ04/TQ05
        Tq04Tq05 tq04Tq05 = new Tq04Tq05(data);
        // Setando o ponto
        tq04Tq05.setPonto(ponto);
        // Associando a instância de TQ04/TQ05 criada a coleta
        coleta.getTq04Tq05Set().add(tq04Tq05);

        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());

        // Salvando no banco a instância de TQ04/TQ05
        tq04Tq05Repository.save(tq04Tq05);
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);


        return new Tq04Tq05DTO(tq04Tq05);
    }
}
