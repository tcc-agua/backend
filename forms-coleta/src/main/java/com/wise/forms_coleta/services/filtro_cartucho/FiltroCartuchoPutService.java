package com.wise.forms_coleta.services.filtro_cartucho;

import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoDTO;
import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoPutDTO;

public interface FiltroCartuchoPutService {
    FiltroCartuchoDTO put(Long id, FiltroCartuchoPutDTO data);
}
