package com.wise.forms_coleta.services.bomba_bc03;

import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03DTO;
import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03PutDTO;

public interface BombaBc03PutService {
    BombaBc03DTO put(Long id, BombaBc03PutDTO data);
}
