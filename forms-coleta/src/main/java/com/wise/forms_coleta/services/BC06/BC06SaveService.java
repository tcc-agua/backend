package com.wise.forms_coleta.services.BC06;

import com.wise.forms_coleta.dtos.BC06.BC06CreateDTO;
import com.wise.forms_coleta.dtos.BC06.BC06DTO;

public interface BC06SaveService {
    BC06DTO save(BC06CreateDTO data);
}
