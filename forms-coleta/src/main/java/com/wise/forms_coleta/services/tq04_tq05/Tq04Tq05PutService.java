package com.wise.forms_coleta.services.tq04_tq05;

import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05DTO;
import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05PutDTO;

public interface Tq04Tq05PutService {
    Tq04Tq05DTO put(Long id, Tq04Tq05PutDTO data);
}
