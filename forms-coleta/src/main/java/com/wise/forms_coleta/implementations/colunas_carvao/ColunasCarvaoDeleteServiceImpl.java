package com.wise.forms_coleta.implementations.colunas_carvao;

import com.wise.forms_coleta.entities.ColunasCarvao;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColunasCarvaoRepository;
import com.wise.forms_coleta.services.colunas_carvao.ColunasCarvaoDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColunasCarvaoDeleteServiceImpl implements ColunasCarvaoDeleteService {
    @Autowired
    private ColunasCarvaoRepository colunasCarvaoRepository;

    @Override
    public String delete(Long id) {
        ColunasCarvao colunasCarvao = colunasCarvaoRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        colunasCarvaoRepository.delete(colunasCarvao);
        return "Formulário deletado com sucesso!";
    }
}
