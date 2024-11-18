package com.wise.forms_coleta.implementations.horimetro;

import com.wise.forms_coleta.dtos.horimetro.HorimetroCreateDTO;
import com.wise.forms_coleta.dtos.horimetro.HorimetroDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Horimetro;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.HorimetroRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.horimetro.HorimetroSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class HorimetroSaveServiceImpl implements HorimetroSaveService {

    @Autowired
    private HorimetroRepository horimetroRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    // Método de salvar nova coleta de horimetro
    @Override
    public HorimetroDTO save(HorimetroCreateDTO data) {
        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade horimetro
        Horimetro horimetro = new Horimetro(data);
        // Setando o ponto
        horimetro.setPonto(ponto);
        // Associando a instância de horimetro criada a coleta
        coleta.getHorimetroSet().add(horimetro);

        // Salvando no banco a instância de horimetro
        horimetroRepository.save(horimetro);
        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new HorimetroDTO(horimetro);
    }
}
