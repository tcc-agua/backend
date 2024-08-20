package com.wise.forms_coleta.services.BH02;

import com.wise.forms_coleta.dtos.bh02.BH02DTO;
import com.wise.forms_coleta.dtos.bh02.BH02PutDTO;

public interface BH02PutService {
    BH02DTO put(Long id, BH02PutDTO data);
}
