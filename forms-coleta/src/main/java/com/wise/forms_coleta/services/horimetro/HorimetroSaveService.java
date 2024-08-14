package com.wise.forms_coleta.services.horimetro;

import com.wise.forms_coleta.dtos.horimetro.HorimetroCreateDTO;
import com.wise.forms_coleta.dtos.horimetro.HorimetroDTO;

public interface HorimetroSaveService {
    HorimetroDTO save(HorimetroCreateDTO data);
}
