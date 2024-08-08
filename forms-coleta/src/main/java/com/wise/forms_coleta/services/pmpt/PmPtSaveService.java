package com.wise.forms_coleta.services.pmpt;

import com.wise.forms_coleta.dtos.PmPt.PmPtCreateDTO;
import com.wise.forms_coleta.dtos.PmPt.PmPtDTO;

public interface PmPtSaveService {

    PmPtDTO save (PmPtCreateDTO data);

}
