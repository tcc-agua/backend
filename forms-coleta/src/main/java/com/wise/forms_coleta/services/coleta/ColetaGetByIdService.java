package com.wise.forms_coleta.services.coleta;

import com.wise.forms_coleta.dtos.coleta.ColetaDTO;

public interface ColetaGetByIdService {
    ColetaDTO getById(Long id);
}
