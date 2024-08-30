package com.wise.forms_coleta.services.exportar_excel;

import java.io.IOException;
import java.util.List;

public interface GetExcelDataService {
    List<List<String>> readExcelFile(String sheetName) throws IOException;
}
