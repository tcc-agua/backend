package com.wise.forms_coleta.implementations.colunas_carvao;

import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoCreateDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.ColunasCarvao;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.ColunasCarvaoRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.colunas_carvao.ColunasCarvaoSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ColunasCarvaoSaveServiceImpl implements ColunasCarvaoSaveService {
    @Autowired
    private ColunasCarvaoRepository colunasCarvaoRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public ColunasCarvaoDTO save(ColunasCarvaoCreateDTO data) {

        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        ColunasCarvao colunasCarvao = new ColunasCarvao(data);
        colunasCarvao.setPonto(ponto);

        coleta.getColunasCarvaoSet().add(colunasCarvao);

        coleta.setHora_fim(LocalTime.now());

        colunasCarvaoRepository.save(colunasCarvao);
        coletaRepository.save(coleta);

        return new ColunasCarvaoDTO(colunasCarvao);
    }
}
