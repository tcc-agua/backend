package com.wise.forms_coleta.services.BC06;

import com.wise.forms_coleta.dtos.BC06.BC06DTO;
import com.wise.forms_coleta.dtos.BC06.BC06PutDTO;

public interface BC06PutService {
    BC06DTO put(Long id, BC06PutDTO data);
}
