package com.wise.forms_coleta.services.BC01;

import com.wise.forms_coleta.dtos.bc01.BC01DTO;
import com.wise.forms_coleta.dtos.bc01.BC01PutDTO;

public interface BC01PutService {
    BC01DTO put(Long id, BC01PutDTO data);
}
