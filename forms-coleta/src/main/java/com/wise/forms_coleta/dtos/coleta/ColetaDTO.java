package com.wise.forms_coleta.dtos.coleta;

import com.wise.forms_coleta.entities.Coleta;

import java.time.LocalDate;
import java.time.LocalTime;

public record ColetaDTO(
        Long id,
        String tecnico,
        LocalDate dataColeta,
        LocalTime horaInicio,
        LocalTime horaFim
){

    public ColetaDTO(Coleta coleta){
        this(coleta.getId(), coleta.getTecnico(), coleta.getDataColeta(), coleta.getHora_inicio(), coleta.getHora_fim());
    }

}
