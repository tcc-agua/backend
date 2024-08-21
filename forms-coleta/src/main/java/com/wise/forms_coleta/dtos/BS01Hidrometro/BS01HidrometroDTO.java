package com.wise.forms_coleta.dtos.BS01Hidrometro;

import com.wise.forms_coleta.entities.BS01Hidrometro;
import com.wise.forms_coleta.entities.Ponto;

public record BS01HidrometroDTO (
        Long id,
        Integer volume,
        Ponto ponto
) {

    public BS01HidrometroDTO(BS01Hidrometro data) {
        this(data.getId(), data.getVolume(), data.getPonto());
    }
}
