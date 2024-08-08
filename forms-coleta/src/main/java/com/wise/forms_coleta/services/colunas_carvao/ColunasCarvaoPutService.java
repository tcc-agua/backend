package com.wise.forms_coleta.services.colunas_carvao;

import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoCreateDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoPutDTO;

public interface ColunasCarvaoPutService {
    ColunasCarvaoDTO put(Long id, ColunasCarvaoPutDTO data);
}
