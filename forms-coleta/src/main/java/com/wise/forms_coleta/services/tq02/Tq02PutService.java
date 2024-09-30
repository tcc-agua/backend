package com.wise.forms_coleta.services.tq02;

import com.wise.forms_coleta.dtos.TQ02.TQ02DTO;
import com.wise.forms_coleta.dtos.TQ02.TQ02PutDTO;

public interface Tq02PutService {
    TQ02DTO put(Long id, TQ02PutDTO data);
}
