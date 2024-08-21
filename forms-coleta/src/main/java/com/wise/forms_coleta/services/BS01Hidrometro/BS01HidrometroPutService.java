package com.wise.forms_coleta.services.BS01Hidrometro;

import com.wise.forms_coleta.dtos.BS01Hidrometro.BS01HidrometroDTO;
import com.wise.forms_coleta.dtos.BS01Hidrometro.BS01HidrometroPutDTO;

public interface BS01HidrometroPutService {
    BS01HidrometroDTO put (Long id, BS01HidrometroPutDTO data);
}
