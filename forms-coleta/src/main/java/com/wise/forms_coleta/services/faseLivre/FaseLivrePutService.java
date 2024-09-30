package com.wise.forms_coleta.services.faseLivre;

import com.wise.forms_coleta.dtos.faseLivre.FaseLivreDTO;
import com.wise.forms_coleta.dtos.faseLivre.FaseLivrePutDTO;

public interface FaseLivrePutService {
    FaseLivreDTO put(Long id, FaseLivrePutDTO data);
}
