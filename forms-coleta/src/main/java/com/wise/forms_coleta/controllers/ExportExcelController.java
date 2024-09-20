package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.implementations.excel.exportar_excel.ExcelExportServiceImpl;
import com.wise.forms_coleta.services.exportar_excel.GetExcelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/exportExcel")
public class ExportExcelController {
    @Autowired
    ExcelExportServiceImpl excelExportService;

    @Autowired
    GetExcelDataService getExcelDataService;

    @GetMapping()
    public ResponseEntity<ByteArrayResource> exportToExcel(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        try {
            ByteArrayResource excelFile = excelExportService.exportToExcel(startDate, endDate);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=coletas.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(excelFile.contentLength())
                    .body(excelFile);
        } catch (IOException e) {
            e.printStackTrace(); // Adicione logs para ajudar na depuração
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @GetMapping("/data/{sheetName}")
//    public List<List<String>> getExcelData(@PathVariable String sheetName) throws IOException {
//        return getExcelDataService.readExcelFile(sheetName);
//    }
}
