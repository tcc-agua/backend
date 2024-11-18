package com.wise.forms_coleta.implementations.filtro_cartucho;

import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoCreateDTO;
import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.FiltroCartucho;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.FiltroCartuchoRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.filtro_cartucho.FiltroCartuchoSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class FiltroCartuchoSaveServiceImpl implements FiltroCartuchoSaveService {
    @Autowired
    private FiltroCartuchoRepository filtroCartuchoRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    // Método de salvar nova coleta de filtro cartucho
    @Override
    public FiltroCartuchoDTO save(FiltroCartuchoCreateDTO data) {

        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade filtro cartucho
        FiltroCartucho filtroCartucho = new FiltroCartucho(data);
        // Setando o ponto
        filtroCartucho.setPonto(ponto);
        // Associando a instância de filtro cartucho criada a coleta
        coleta.getFiltroCartuchoSet().add(filtroCartucho);

        // Salvando no banco a instância de filtro cartucho
        filtroCartuchoRepository.save(filtroCartucho);
        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new FiltroCartuchoDTO(filtroCartucho);
    }
}
