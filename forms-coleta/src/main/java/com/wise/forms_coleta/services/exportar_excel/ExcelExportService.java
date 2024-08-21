package com.wise.forms_coleta.services.exportar_excel;

import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;

public interface ExcelExportService {
    ByteArrayResource exportToExcel() throws IOException;
}
