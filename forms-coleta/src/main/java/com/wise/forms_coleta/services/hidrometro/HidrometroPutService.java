package com.wise.forms_coleta.services.hidrometro;

import com.wise.forms_coleta.dtos.hidrometro.HidrometroDTO;
import com.wise.forms_coleta.dtos.hidrometro.HidrometroPutDTO;

public interface HidrometroPutService {
    HidrometroDTO put(Long id, HidrometroPutDTO data);
}
