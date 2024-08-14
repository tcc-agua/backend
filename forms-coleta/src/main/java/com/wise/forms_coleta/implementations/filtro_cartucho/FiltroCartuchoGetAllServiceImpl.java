package com.wise.forms_coleta.implementations.filtro_cartucho;

import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoDTO;
import com.wise.forms_coleta.repositories.FiltroCartuchoRepository;
import com.wise.forms_coleta.services.filtro_cartucho.FiltroCartuchoGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiltroCartuchoGetAllServiceImpl implements FiltroCartuchoGetAllService {
    @Autowired
    private FiltroCartuchoRepository filtroCartuchoRepository;


    @Override
    public List<FiltroCartuchoDTO> getAll() {
        return filtroCartuchoRepository.findAll().stream().map(FiltroCartuchoDTO::new).toList();
    }
}
