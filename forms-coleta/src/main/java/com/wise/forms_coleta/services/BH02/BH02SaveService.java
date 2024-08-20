package com.wise.forms_coleta.services.BH02;

import com.wise.forms_coleta.dtos.bh02.BH02CreateDTO;
import com.wise.forms_coleta.dtos.bh02.BH02DTO;

public interface BH02SaveService {
    BH02DTO save(BH02CreateDTO data);
}
