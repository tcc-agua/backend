package com.wise.forms_coleta.dtos.tq04_tq05;

import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.entities.Tq04Tq05;

public record Tq04Tq05DTO(

    Long id,
    Boolean houve_preparo_solucao,
    Integer qtd_bombonas,
    Double kg_bombonas,
    Double horimetro,
    Double hidrometro,
    Ponto ponto
) {

    public Tq04Tq05DTO(Tq04Tq05 tq04Tq05){
        this(tq04Tq05.getId(), tq04Tq05.getHouve_preparo_solucao(), tq04Tq05.getQtd_bombonas(), tq04Tq05.getKg_bombonas(), tq04Tq05.getHorimetro(), tq04Tq05.getHidrometro(), tq04Tq05.getPonto());
    }
}
