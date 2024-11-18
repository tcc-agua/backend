package com.wise.forms_coleta.implementations.bomba_bc03;

import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03CreateDTO;
import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03DTO;
import com.wise.forms_coleta.entities.BombaBc03;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BombaBc03Repository;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.bomba_bc03.BombaBc03SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class BombaBc03SaveServiceImpl implements BombaBc03SaveService {

    @Autowired
    private PontoRepository pontoRepository;
    @Autowired
    private ColetaRepository coletaRepository;
    @Autowired
    private BombaBc03Repository bombaBc03Repository;

    // Método de salvar nova coleta de Bomba bc 03
    @Override
    public BombaBc03DTO save(BombaBc03CreateDTO data) {

        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade BC01
        BombaBc03 bombaBc03 = new BombaBc03(data);
        // Setando o ponto
        bombaBc03.setPonto(ponto);
        // Associando a instância de BC01 criada a coleta
        coleta.getBombaBc03Set().add(bombaBc03);

        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());

        // Salvando no banco a instância de BC01
        bombaBc03Repository.save(bombaBc03);
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new BombaBc03DTO(bombaBc03);
    }
}
