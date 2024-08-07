package com.wise.forms_coleta.implementations.colunas_carvao;

import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoCreateDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.entities.ColunasCarvao;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColunasCarvaoRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.colunas_carvao.ColunasCarvaoSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColunasCarvaoSaveServiceImpl implements ColunasCarvaoSaveService {
    @Autowired
    private ColunasCarvaoRepository colunasCarvaoRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public ColunasCarvaoDTO save(ColunasCarvaoCreateDTO data) {
        if(pontoRepository.findByNome(data.nome()).isPresent()){
            ColunasCarvao colunasCarvao = new ColunasCarvao(data);
            colunasCarvao.setFk_ponto(pontoRepository.findByNome(data.nome()).get());
            colunasCarvaoRepository.save(colunasCarvao);
            return new ColunasCarvaoDTO(colunasCarvao);
        }
        throw new GenericsNotFoundException("Ponto não encontrado!");
    }
}
