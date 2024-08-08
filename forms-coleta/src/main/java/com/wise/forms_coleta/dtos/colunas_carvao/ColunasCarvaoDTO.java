package com.wise.forms_coleta.dtos.colunas_carvao;

import com.wise.forms_coleta.entities.ColunasCarvao;
import com.wise.forms_coleta.entities.Ponto;

public record ColunasCarvaoDTO(
        Long id,
        Double pressao_c01,
        Double pressao_c02,
        Double pressao_c03,
        Double pressao_saida,
        Boolean houve_troca_carvao,
        Boolean houve_retrolavagem,
        Ponto ponto
){
    public ColunasCarvaoDTO(ColunasCarvao data){
        this(data.getId(), data.getPressao_c01(), data.getPressao_c02(), data.getPressao_c03(), data.getPressao_saida(), data.getHouve_troca_carvao(), data.getHouve_retrolavagem(), data.getFk_ponto());
    }
}
