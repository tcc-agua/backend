package com.wise.forms_coleta.dtos.faseLivre;

import com.wise.forms_coleta.entities.FaseLivre;
import com.wise.forms_coleta.entities.Ponto;

public record FaseLivreDTO(
        Long id,
        Double volume,
        Boolean houve_troca,
        Ponto ponto

) {
    public FaseLivreDTO(FaseLivre faseLivre){
        this(faseLivre.getId(), faseLivre.getVolume(), faseLivre.getHouve_troca(), faseLivre.getPonto());
    }
}
