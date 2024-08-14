package com.wise.forms_coleta.dtos.cd;

import com.wise.forms_coleta.entities.CD;
import com.wise.forms_coleta.entities.Ponto;

public record CDDTO (
        Long id,
        String tipo_rede,
        Double pressao,
        Integer hidrometro,
        Ponto ponto
    ){

    public CDDTO(CD data) {
        this(data.getId(), data.getTipo_rede(), data.getPressao(), data.getHidrometro(), data.getPonto());
    }

}
