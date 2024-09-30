package com.wise.forms_coleta.services.pmpt;

import com.wise.forms_coleta.dtos.PmPt.PmPtDTO;
import com.wise.forms_coleta.dtos.PmPt.PmPtPutDTO;

public interface PmPtPutService {
    PmPtDTO put(Long id, PmPtPutDTO data);
}
