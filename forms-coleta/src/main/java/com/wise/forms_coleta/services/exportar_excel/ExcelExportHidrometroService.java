package com.wise.forms_coleta.services.exportar_excel;

import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.time.LocalDate;

public interface ExcelExportHidrometroService {
    ByteArrayResource exportToExcel(LocalDate startDate, LocalDate endDate) throws IOException;
}
