package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.implementations.exportar_excel.ExcelExportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/exportExcel")
public class ExportExcelController {
    @Autowired
    ExcelExportServiceImpl excelExportService;

    @GetMapping
    public ResponseEntity<ByteArrayResource> exportToExcel(){
        try {
            ByteArrayResource excelFile = excelExportService.exportToExcel();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=coletas.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(excelFile.contentLength())
                    .body(excelFile);
        } catch (IOException e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
