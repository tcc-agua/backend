package com.wise.forms_coleta.services.bomba_bc03;

import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03CreateDTO;
import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03DTO;

public interface BombaBc03SaveService {
    BombaBc03DTO save(BombaBc03CreateDTO data);
}
