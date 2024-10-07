package com.wise.forms_coleta.implementations.coleta;

import com.wise.forms_coleta.dtos.coleta.ColetaCreateDTO;
import com.wise.forms_coleta.dtos.coleta.ColetaDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.services.coleta.ColetaPatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColetaPutServiceImpl implements ColetaPatchService {
    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public ColetaDTO patch(Long id, ColetaCreateDTO data) {
        Coleta coleta = coletaRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Coleta não encontrada!"));

        if(!(data.tecnico() == null)){
            coleta.setTecnico(data.tecnico());
        }
        if(data.horaInicio() != null){
            coleta.setHora_inicio(data.horaInicio());
        }
        if(data.dataColeta() != null){
            coleta.setDataColeta(data.dataColeta());
        }
        if (data.horaFim() != null){
            coleta.setHora_fim(data.horaFim());
        }

        coletaRepository.save(coleta);
        return new ColetaDTO(coleta);
    }
}
