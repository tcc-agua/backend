package com.wise.forms_coleta.services.BS01Pressao;

import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoDTO;
import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoPutDTO;

public interface BS01PressaoPutService {
    BS01PressaoDTO put(Long id, BS01PressaoPutDTO data);
}
