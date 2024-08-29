package com.wise.forms_coleta.services.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoDTO;

import java.util.List;

public interface PontoGetAllByExcelService {
    List<PontoDTO> getAllPointsByExcel(String excel);
}
