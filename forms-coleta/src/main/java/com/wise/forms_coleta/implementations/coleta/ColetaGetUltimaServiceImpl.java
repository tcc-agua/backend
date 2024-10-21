package com.wise.forms_coleta.implementations.coleta;

import com.wise.forms_coleta.dtos.coleta.ColetaDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.services.coleta.ColetaGetUltimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColetaGetUltimaServiceImpl  implements ColetaGetUltimaService {

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public ColetaDTO getLast() {
        List<Coleta> coletas = coletaRepository.findAll();
        Coleta ultimaColeta = coletas.isEmpty() ? null : coletas.get(coletas.size() - 1);

        if(ultimaColeta != null){
            return new ColetaDTO(ultimaColeta);
        }

        throw new GenericsNotFoundException("Coleta não encontrada");
    }
}
