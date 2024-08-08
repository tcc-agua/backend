package com.wise.forms_coleta.implementations.coleta;

import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.services.coleta.ColetaDeleteService;
import org.springframework.beans.factory.annotation.Autowired;

public class ColetaDeleteServiceImpl implements ColetaDeleteService {
    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public String delete(Long id) {
        Coleta coleta = coletaRepository.findById(id).orElseThrow(() -> new GenericsNotFoundException("Coleta não encontrada!"));
        coletaRepository.delete(coleta);
        return "Coleta deletada com sucesso!";
    }
}
