package com.wise.forms_coleta.dtos.hidrometro;

import com.wise.forms_coleta.entities.Hidrometro;
import com.wise.forms_coleta.entities.Ponto;

public record HidrometroDTO(Long id, Double volume, Ponto ponto) {
    public HidrometroDTO(Hidrometro hidrometro) {
        this(hidrometro.getId(),hidrometro.getVolume(), hidrometro.getPonto());
    }
}
