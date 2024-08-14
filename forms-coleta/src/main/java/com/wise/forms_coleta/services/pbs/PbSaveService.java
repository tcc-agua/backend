package com.wise.forms_coleta.services.pbs;

import com.wise.forms_coleta.dtos.pbs.PbCreateDTO;
import com.wise.forms_coleta.dtos.pbs.PbDTO;

public interface PbSaveService {

    PbDTO save(PbCreateDTO data);
}
