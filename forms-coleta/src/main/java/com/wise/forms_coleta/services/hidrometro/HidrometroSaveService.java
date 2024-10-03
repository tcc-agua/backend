package com.wise.forms_coleta.services.hidrometro;

import com.wise.forms_coleta.dtos.hidrometro.HidrometroCreateDTO;
import com.wise.forms_coleta.dtos.hidrometro.HidrometroDTO;

public interface HidrometroSaveService {

    HidrometroDTO save(HidrometroCreateDTO data);
}
