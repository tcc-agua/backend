package com.wise.forms_coleta.services.BS01Pressao;

import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoCreateDTO;
import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoDTO;

public interface BS01PressaoSaveService {
    BS01PressaoDTO save(BS01PressaoCreateDTO data);
}
