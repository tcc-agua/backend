package com.wise.forms_coleta.services.BC01;

import com.wise.forms_coleta.dtos.bc01.BC01CreateDTO;
import com.wise.forms_coleta.dtos.bc01.BC01DTO;

public interface BC01SaveService {

    BC01DTO save(BC01CreateDTO data);
}
