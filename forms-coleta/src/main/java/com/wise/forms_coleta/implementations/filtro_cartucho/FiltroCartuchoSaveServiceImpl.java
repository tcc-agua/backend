package com.wise.forms_coleta.implementations.filtro_cartucho;

import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoCreateDTO;
import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoDTO;
import com.wise.forms_coleta.entities.FiltroCartucho;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.FiltroCartuchoRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.filtro_cartucho.FiltroCartuchoSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FiltroCartuchoSaveServiceImpl implements FiltroCartuchoSaveService {
    @Autowired
    private FiltroCartuchoRepository filtroCartuchoRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public FiltroCartuchoDTO save(FiltroCartuchoCreateDTO data) {
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));
        FiltroCartucho filtroCartucho = new FiltroCartucho(data);
        filtroCartucho.setPonto(ponto);
        filtroCartuchoRepository.save(filtroCartucho);
        return new FiltroCartuchoDTO(filtroCartucho);
    }
}
