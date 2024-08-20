package com.wise.forms_coleta.dtos.BS01Pressao;

import com.wise.forms_coleta.entities.BS01Pressao;
import com.wise.forms_coleta.entities.Ponto;

public record BS01PressaoDTO(
        Long id,
        Double pressao,
        Ponto ponto
) {
    public BS01PressaoDTO(BS01Pressao data) {
        this(data.getId(), data.getPressao(), data.getPonto());
    }
}
