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

    @Override
    public BS01PressaoDTO save(BS01PressaoCreateDTO data) {
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        Coleta coleta = new Coleta(data.nomeTecnico(), LocalDate.now(), LocalTime.now());

        BS01Pressao bs01Pressao = new BS01Pressao(data);
        bs01Pressao.setPonto(ponto);

        coleta.getBs01PressaoSet().add(bs01Pressao);

        coleta.setHora_fim(LocalTime.now());

        bs01PressaoRepository.save(bs01Pressao);
        coletaRepository.save(coleta);

        return new BS01PressaoDTO(bs01Pressao);

    }

}
