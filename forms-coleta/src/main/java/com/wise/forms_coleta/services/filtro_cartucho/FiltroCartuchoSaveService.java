package com.wise.forms_coleta.services.filtro_cartucho;

import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoCreateDTO;
import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoDTO;

public interface FiltroCartuchoSaveService {
    FiltroCartuchoDTO save(FiltroCartuchoCreateDTO data);
}
