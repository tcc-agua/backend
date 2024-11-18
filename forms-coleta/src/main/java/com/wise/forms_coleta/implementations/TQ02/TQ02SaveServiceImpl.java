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

    // Método de salvar nova coleta de TQ02
    @Override
    public TQ02DTO save(TQ02CreateDTO data) {

        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade TQ02
        TQ02 tq02 = new TQ02(data);
        // Setando o ponto
        tq02.setPonto(ponto);
        // Associando a instância de TQ02 criada a coleta
        coleta.getTq02Set().add(tq02);

        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());

        // Salvando no banco a instância de TQ02
        tq02Repository.save(tq02);
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new TQ02DTO(tq02);

    }
}
