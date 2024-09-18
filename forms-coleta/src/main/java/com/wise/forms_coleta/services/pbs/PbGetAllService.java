package com.wise.forms_coleta.services.pbs;

import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.dtos.pbs.PbDTO;

import java.util.List;

public interface PbGetAllService {
    List<PbDTO> getAll();
}
