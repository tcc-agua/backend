package com.wise.forms_coleta.implementations.filtro_cartucho;

import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoDTO;
import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoPutDTO;
import com.wise.forms_coleta.entities.FiltroCartucho;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.FiltroCartuchoRepository;
import com.wise.forms_coleta.services.filtro_cartucho.FiltroCartuchoPutService;
import org.springframework.beans.factory.annotation.Autowired;

public class FiltroCartuchoPutServiceImpl implements FiltroCartuchoPutService {
    @Autowired
    private FiltroCartuchoRepository filtroCartuchoRepository;


    @Override
    public FiltroCartuchoDTO put(Long id, FiltroCartuchoPutDTO data) {
        FiltroCartucho filtroCartucho = filtroCartuchoRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));
        filtroCartucho.setPressao_entrada(data.pressao_entrada());
        filtroCartucho.setPressao_saida(data.pressao_saida());
        filtroCartuchoRepository.save(filtroCartucho);
        return new FiltroCartuchoDTO(filtroCartucho);
    }
}
