package com.wise.forms_coleta.implementations.BC06;

import com.wise.forms_coleta.dtos.BC06.BC06CreateDTO;
import com.wise.forms_coleta.dtos.BC06.BC06DTO;
import com.wise.forms_coleta.entities.BC06;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BC06Repository;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.BC06.BC06SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class BC06SaveServiceImpl implements BC06SaveService {
    @Autowired
    private BC06Repository bc06Repository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    // Método de salvar nova coleta de BC06
    @Override
    public BC06DTO save(BC06CreateDTO data) {
        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade BC06
        BC06 bc06 = new BC06(data);
        // Setando o ponto
        bc06.setPonto(ponto);
        // Associando a instância de BC01 criada a coleta
        coleta.getBc06Set().add(bc06);

        // Salvando no banco a instância de BC01
        bc06Repository.save(bc06);
        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new BC06DTO(bc06);
    }
}
