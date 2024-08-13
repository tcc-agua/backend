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
        List<StatusEnum> statusList = List.of(StatusEnum.NAO_COLETADO, StatusEnum.COLETADO);

        return pontoRepository.findAllByStatusEnumIn(statusList)
                .stream()
                .map(PontoDTO::new)
                .toList();
    }

}
