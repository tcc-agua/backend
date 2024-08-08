package com.wise.forms_coleta.implementations.coleta;

import com.wise.forms_coleta.dtos.coleta.ColetaCreateDTO;
import com.wise.forms_coleta.dtos.coleta.ColetaDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.services.coleta.ColetaDeleteService;
import com.wise.forms_coleta.services.coleta.ColetaPutService;
import org.springframework.beans.factory.annotation.Autowired;

public class ColetaPutServiceImpl implements ColetaPutService {
    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public ColetaDTO put(Long id, ColetaCreateDTO data) {
        Coleta coleta = coletaRepository.findById(id).orElseThrow(() -> new GenericsNotFoundException("Coleta não encontrada!"));
        coleta.setTecnico(data.tecnico());
        coleta.setDataColeta(data.dataColeta());
        coleta.setHoraInicio(data.horaInicio());
        coleta.setHoraFim(data.horaFim());
        coletaRepository.save(coleta);
        return new ColetaDTO(coleta);
    }
}
