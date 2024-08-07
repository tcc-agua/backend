package com.wise.forms_coleta.services.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoDTO;
import com.wise.forms_coleta.dtos.ponto.PontoPutDTO;

public interface PontoPutService {
    PontoDTO put(String name, PontoPutDTO data);
}
