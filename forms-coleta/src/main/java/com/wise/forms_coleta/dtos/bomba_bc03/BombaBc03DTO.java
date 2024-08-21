package com.wise.forms_coleta.dtos.bomba_bc03;

import com.wise.forms_coleta.entities.BombaBc03;
import com.wise.forms_coleta.entities.Ponto;

public record BombaBc03DTO(
        Long id,
        Double pressao,
        Double hidrometro,
        Double horimetro,
        Ponto ponto
) {
    public BombaBc03DTO(BombaBc03 bombaBc03){
        this(bombaBc03.getId(), bombaBc03.getPressao(), bombaBc03.getHidrometro(), bombaBc03.getHorimetro(), bombaBc03.getPonto());
    }
}
