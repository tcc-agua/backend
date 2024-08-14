package com.wise.forms_coleta.services.CD;

import com.wise.forms_coleta.dtos.cd.CDCreateDTO;
import com.wise.forms_coleta.dtos.cd.CDDTO;

public interface CDSaveService {
    CDDTO save(CDCreateDTO data);
}
