package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.implementations.excel.exportar_excel.ExcelExportHidrometroServiceImpl;
import com.wise.forms_coleta.implementations.excel.exportar_excel.ExcelExportServiceImpl;
import com.wise.forms_coleta.services.exportar_excel.ExcelExportHidrometroService;
import com.wise.forms_coleta.services.exportar_excel.ExcelExportService;
import com.wise.forms_coleta.services.exportar_excel.GetExcelDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Excel")
public class ExportExcelController {
    @Autowired
    ExcelExportService excelExportService;

    @Autowired
    ExcelExportHidrometroService excelExportHidrometroService;

    @GetMapping
    public ResponseEntity<ByteArrayResource> exportToExcel(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        System.out.println("DATA INICIO: " + startDate + "\nDATA FIM: " + endDate);
        System.out.println("TIPO DATA INICIO: " + startDate.getClass() + "\nTIPO DATA FIM: "+ endDate.getClass());
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

    @GetMapping("/hidrometro")
    public ResponseEntity<ByteArrayResource> exportToExcelHidrometro(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        System.out.println("DATA INICIO: " + startDate + "\nDATA FIM: " + endDate);
        System.out.println("TIPO DATA INICIO: " + startDate.getClass() + "\nTIPO DATA FIM: "+ endDate.getClass());
        try {
            ByteArrayResource excelFile = excelExportHidrometroService.exportToExcel(startDate, endDate);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=coletas_hidrometro.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(excelFile.contentLength())
                    .body(excelFile);
        } catch (IOException e) {
            e.printStackTrace(); // Adicione logs para ajudar na depuração
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
