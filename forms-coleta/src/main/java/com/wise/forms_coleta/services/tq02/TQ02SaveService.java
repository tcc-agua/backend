package com.wise.forms_coleta.services.tq02;

import com.wise.forms_coleta.dtos.TQ02.TQ02CreateDTO;
import com.wise.forms_coleta.dtos.TQ02.TQ02DTO;

public interface TQ02SaveService {
    TQ02DTO save(TQ02CreateDTO data);
}
