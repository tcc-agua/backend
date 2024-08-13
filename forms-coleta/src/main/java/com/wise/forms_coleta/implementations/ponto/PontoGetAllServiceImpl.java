package com.wise.forms_coleta.implementations.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoDTO;
import com.wise.forms_coleta.entities.enums.StatusEnum;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.ponto.PontoGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PontoGetAllServiceImpl implements PontoGetAllService {

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public List<PontoDTO> getAll() {

        return pontoRepository.findAll()
                .stream()
                .filter(ponto -> ponto.getStatus() == StatusEnum.COLETADO || ponto.getStatus() == StatusEnum.NAO_COLETADO)
                .map(PontoDTO::new)
                .toList();
    }

}
