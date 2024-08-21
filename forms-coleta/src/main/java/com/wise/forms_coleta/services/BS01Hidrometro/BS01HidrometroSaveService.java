package com.wise.forms_coleta.services.BS01Hidrometro;

import com.wise.forms_coleta.dtos.BS01Hidrometro.BS01HidrometroCreateDTO;
import com.wise.forms_coleta.dtos.BS01Hidrometro.BS01HidrometroDTO;

public interface BS01HidrometroSaveService {
    BS01HidrometroDTO save(BS01HidrometroCreateDTO data);
}
