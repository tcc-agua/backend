package com.wise.forms_coleta.implementations.BS01Pressao;

import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoCreateDTO;
import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoDTO;
import com.wise.forms_coleta.entities.BS01Pressao;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BS01PressaoRepository;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.BS01Pressao.BS01PressaoSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class BS01PressaoSaveServiceImpl implements BS01PressaoSaveService {

    @Autowired
    BS01PressaoRepository bs01PressaoRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    // Método de salvar nova coleta de BS01 Pressao
    @Override
    public BS01PressaoDTO save(BS01PressaoCreateDTO data) {
        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));
        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade BS01 Pressao
        BS01Pressao bs01Pressao = new BS01Pressao(data);
        // Setando o ponto
        bs01Pressao.setPonto(ponto);
        // Associando a instância de BS01 Pressao criada a coleta
        coleta.getBs01PressaoSet().add(bs01Pressao);
        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());

        // Salvando no banco a instância de BC01
        bs01PressaoRepository.save(bs01Pressao);
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new BS01PressaoDTO(bs01Pressao);

    }

}
