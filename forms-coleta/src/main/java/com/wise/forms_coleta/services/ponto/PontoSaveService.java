package com.wise.forms_coleta.services.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoCreateDTO;
import com.wise.forms_coleta.dtos.ponto.PontoDTO;

public interface PontoSaveService {

    PontoDTO save(PontoCreateDTO data);

}
