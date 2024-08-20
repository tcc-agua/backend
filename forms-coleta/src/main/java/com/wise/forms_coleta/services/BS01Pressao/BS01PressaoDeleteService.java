package com.wise.forms_coleta.services.BS01Pressao;

import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoPutDTO;

public interface BS01PressaoDeleteService {

    String delete(Long id);
}
