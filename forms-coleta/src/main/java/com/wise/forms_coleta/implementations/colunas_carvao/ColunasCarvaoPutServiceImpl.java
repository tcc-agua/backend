package com.wise.forms_coleta.implementations.colunas_carvao;


import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoPutDTO;
import com.wise.forms_coleta.entities.ColunasCarvao;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColunasCarvaoRepository;
import com.wise.forms_coleta.services.colunas_carvao.ColunasCarvaoPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColunasCarvaoPutServiceImpl implements ColunasCarvaoPutService {

    @Autowired
    private ColunasCarvaoRepository colunasCarvaoRepository;

    @Override
    public ColunasCarvaoDTO put(Long id, ColunasCarvaoPutDTO data) {
        ColunasCarvao colunasCarvao = colunasCarvaoRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        colunasCarvao.setPressao_c01(data.presssao_c01());
        colunasCarvao.setPressao_c02(data.pressao_c02());
        colunasCarvao.setPressao_c03(data.pressao_c03());
        colunasCarvao.setPressao_saida(data.pressao_saida());
        colunasCarvao.setHouve_troca_carvao(data.houve_troca_carvao());
        colunasCarvao.setHouve_retrolavagem(data.houve_retrolavagem());

        colunasCarvaoRepository.save(colunasCarvao);
        return new ColunasCarvaoDTO(colunasCarvao);
    }
}
