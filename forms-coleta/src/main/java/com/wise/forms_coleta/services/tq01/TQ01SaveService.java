package com.wise.forms_coleta.services.tq01;


import com.wise.forms_coleta.dtos.tq01.TQ01CreateDTO;
import com.wise.forms_coleta.dtos.tq01.TQ01DTO;

public interface TQ01SaveService {
    TQ01DTO save(TQ01CreateDTO data);
}
