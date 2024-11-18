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

    // Método de salvar nova coleta de colunas carvão
    @Override
    public ColunasCarvaoDTO save(ColunasCarvaoCreateDTO data) {

        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));
        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade colunas carvão
        ColunasCarvao colunasCarvao = new ColunasCarvao(data);
        // Setando o ponto
        colunasCarvao.setPonto(ponto);
        // Associando a instância de colunas carvão criada a coleta
        coleta.getColunasCarvaoSet().add(colunasCarvao);

        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());

        // Salvando no banco a instância de colunas carvão
        colunasCarvaoRepository.save(colunasCarvao);
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new ColunasCarvaoDTO(colunasCarvao);
    }
}
