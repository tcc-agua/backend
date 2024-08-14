package com.wise.forms_coleta.services.CD;

import com.wise.forms_coleta.dtos.cd.CDDTO;
import com.wise.forms_coleta.dtos.cd.CDPutDTO;

public interface CDPutService {
    CDDTO put(Long id, CDPutDTO data);
}
