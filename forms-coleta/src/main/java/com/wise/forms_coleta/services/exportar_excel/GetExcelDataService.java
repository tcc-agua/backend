package com.wise.forms_coleta.services.exportar_excel;

import com.wise.forms_coleta.entities.Ponto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GetExcelDataService {
    List<List<Object>> readExcelFile(String sheetName);
}
