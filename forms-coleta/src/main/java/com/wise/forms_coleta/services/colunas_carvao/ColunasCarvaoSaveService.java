package com.wise.forms_coleta.services.colunas_carvao;

import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoCreateDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;

public interface ColunasCarvaoSaveService {
    ColunasCarvaoDTO save(ColunasCarvaoCreateDTO data);
}
