package com.wise.forms_coleta.services.tq01;

import com.wise.forms_coleta.dtos.tq01.TQ01DTO;
import com.wise.forms_coleta.dtos.tq01.TQ01PutDTO;

public interface TQ01PutService {
    TQ01DTO put(Long id, TQ01PutDTO data);

}
