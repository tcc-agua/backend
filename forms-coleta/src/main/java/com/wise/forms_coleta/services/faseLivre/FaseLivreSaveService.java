package com.wise.forms_coleta.services.faseLivre;

import com.wise.forms_coleta.dtos.faseLivre.FaseLivreCreateDTO;
import com.wise.forms_coleta.dtos.faseLivre.FaseLivreDTO;

public interface FaseLivreSaveService {
    FaseLivreDTO save(FaseLivreCreateDTO data);
}
