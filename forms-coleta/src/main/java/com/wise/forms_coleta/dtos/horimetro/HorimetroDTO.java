package com.wise.forms_coleta.dtos.horimetro;

import com.wise.forms_coleta.entities.Horimetro;
import com.wise.forms_coleta.entities.Ponto;

public record HorimetroDTO(Long id, String horimetro, Ponto ponto) {
    public HorimetroDTO(Horimetro horimetro){
        this(horimetro.getId(), horimetro.getHorimetro(), horimetro.getPonto());
    }
}
