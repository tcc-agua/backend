package com.wise.forms_coleta.services.coleta;

import com.wise.forms_coleta.dtos.coleta.ColetaCreateDTO;
import com.wise.forms_coleta.dtos.coleta.ColetaDTO;

public interface ColetaSaveService {
    ColetaDTO save(ColetaCreateDTO data);
}