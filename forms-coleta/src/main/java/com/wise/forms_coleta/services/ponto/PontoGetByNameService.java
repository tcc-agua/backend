package com.wise.forms_coleta.services.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoDTO;

public interface PontoGetByNameService {
    PontoDTO getPointByName(String name);
}
