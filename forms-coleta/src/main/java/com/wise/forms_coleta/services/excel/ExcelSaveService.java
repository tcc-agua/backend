package com.wise.forms_coleta.services.excel;

import com.wise.forms_coleta.dtos.excel.ExcelCreateDTO;
import com.wise.forms_coleta.dtos.excel.ExcelDTO;

public interface ExcelSaveService {
    ExcelDTO save(ExcelCreateDTO data);
}
