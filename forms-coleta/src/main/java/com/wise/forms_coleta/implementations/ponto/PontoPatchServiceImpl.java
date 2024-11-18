package com.wise.forms_coleta.implementations.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoDTO;
import com.wise.forms_coleta.dtos.ponto.PontoStatusDTO;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.ponto.PontoPatchStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PontoPatchServiceImpl implements PontoPatchStatusService {

    @Autowired
    private PontoRepository pontoRepository;

    // Método para atualizar um campo do ponto
    @Override
    public PontoDTO patch(String name, PontoStatusDTO data) {

        // recuperando a instância de ponto filtrando pelo nome
        Ponto ponto = pontoRepository.findByNome(name)
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // setando o status do ponto
        ponto.setStatus(data.status());

        // salvando no banco
        pontoRepository.save(ponto);

        return new PontoDTO(ponto);
    }
}
