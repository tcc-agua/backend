package com.wise.forms_coleta.services.horimetro;

import com.wise.forms_coleta.dtos.horimetro.HorimetroDTO;
import com.wise.forms_coleta.dtos.horimetro.HorimetroPutDTO;

public interface HorimetroPutService {
    HorimetroDTO put(Long id, HorimetroPutDTO data);
}
