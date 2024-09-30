package com.wise.forms_coleta.services.pbs;

import com.wise.forms_coleta.dtos.pbs.PbDTO;
import com.wise.forms_coleta.dtos.pbs.PbPutDTO;

public interface PbPutService {
    PbDTO put(Long id, PbPutDTO data);
}
