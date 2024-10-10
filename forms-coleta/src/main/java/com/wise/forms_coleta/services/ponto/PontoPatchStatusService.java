package com.wise.forms_coleta.services.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoDTO;
import com.wise.forms_coleta.dtos.ponto.PontoStatusDTO;

public interface PontoPatchStatusService {
    PontoDTO patch(String name ,PontoStatusDTO data);
}
