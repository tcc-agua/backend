package com.wise.forms_coleta.implementations.colunas_carvao;

import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.repositories.ColunasCarvaoRepository;
import com.wise.forms_coleta.services.colunas_carvao.ColunasCarvaoGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColunasCarvaoGetAllServiceImpl implements ColunasCarvaoGetAllService {
    @Autowired
    private ColunasCarvaoRepository colunasCarvaoRepository;

    @Override
    public List<ColunasCarvaoDTO> getAll() {
        return colunasCarvaoRepository.findAll().stream().map(ColunasCarvaoDTO::new).toList();
    }
}
