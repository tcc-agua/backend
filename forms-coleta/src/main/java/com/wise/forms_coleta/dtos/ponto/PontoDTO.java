package com.wise.forms_coleta.dtos.ponto;

import com.wise.forms_coleta.entities.Excel;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.entities.enums.StatusEnum;

public record PontoDTO(
        Long id,
        String nome,
        String localizacao,
        Excel excel,
        StatusEnum statusEnum

) {

    public PontoDTO(Ponto ponto){
        this(ponto.getId(), ponto.getNome(), ponto.getLocalizacao(), ponto.getExcel(), ponto.getStatus());
    }
}
