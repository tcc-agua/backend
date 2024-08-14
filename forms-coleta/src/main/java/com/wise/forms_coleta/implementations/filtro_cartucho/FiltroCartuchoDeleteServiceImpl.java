package com.wise.forms_coleta.implementations.filtro_cartucho;

import com.wise.forms_coleta.entities.FiltroCartucho;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.FiltroCartuchoRepository;
import com.wise.forms_coleta.services.filtro_cartucho.FiltroCartuchoDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FiltroCartuchoDeleteServiceImpl implements FiltroCartuchoDeleteService {
    @Autowired
    private FiltroCartuchoRepository filtroCartuchoRepository;


    @Override
    public String delete(Long id) {
        FiltroCartucho filtroCartucho = filtroCartuchoRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));
        filtroCartuchoRepository.delete(filtroCartucho);
        return "Formulário deletado com sucesso!";
    }
}
