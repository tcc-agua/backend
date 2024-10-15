package com.wise.forms_coleta.services.exportar_excel;

import java.time.LocalDate;
import java.util.List;

public interface GetExcelHidrometroDataService {
    List<List<Object>> readExcelHidrometroFile(String sheetName, LocalDate startDate, LocalDate endDate, Boolean definicao);
}
