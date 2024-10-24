package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.excel.ExcelCreateDTO;
import com.wise.forms_coleta.dtos.excel.ExcelDTO;
import com.wise.forms_coleta.services.excel.ExcelSaveService;
import com.wise.forms_coleta.services.exportar_excel.GetExcelDataService;
import com.wise.forms_coleta.services.exportar_excel.GetExcelHidrometroDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/excel")
@Tag(name = "Excel", description = "\"API para operações relacionadas ao Excel\"")
public class ExcelController {
    @Autowired
    ExcelSaveService excelSaveService;

    @Autowired
    GetExcelDataService getExcelDataService;

    @Autowired
    GetExcelHidrometroDataService getExcelHidrometroDataService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid ExcelCreateDTO data, UriComponentsBuilder uriBuilder){
        ExcelDTO excelDTO = excelSaveService.save(data);
        URI uri = uriBuilder.path("/excel/{id}").buildAndExpand(excelDTO.id()).toUri();
        return ResponseEntity.created(uri).body(excelDTO);
    }

    @GetMapping("{sheetName}")
    public ResponseEntity<List<List<Object>>> getPontosByExcel(@PathVariable String sheetName, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return new ResponseEntity<>(getExcelDataService.readExcelFile(sheetName, startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("{sheetName}/hidrometro")
    public ResponseEntity<List<List<Object>>> getHidrometrosByExcel(@PathVariable String sheetName, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam Boolean definicao){
        return new ResponseEntity<>(getExcelHidrometroDataService.readExcelHidrometroFile(sheetName, startDate, endDate, definicao), HttpStatus.OK);
    }
}
