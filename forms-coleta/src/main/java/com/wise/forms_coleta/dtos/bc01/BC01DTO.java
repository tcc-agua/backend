package com.wise.forms_coleta.dtos.bc01;

import com.wise.forms_coleta.entities.BC01;
import com.wise.forms_coleta.entities.Ponto;
import org.springframework.web.util.UriComponents;

public record BC01DTO(Long id, Ponto nomePonto, int horimetro, double pressao, int frequencia, double vazao, int volume) {
    public BC01DTO(BC01 bc01) {
        this(bc01.getId(), bc01.getFk_ponto(), bc01.getHorimetro(), bc01.getPressao(), bc01.getFrequencia(), bc01.getVazao(), bc01.getVolume());
    }

}
