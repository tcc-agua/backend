package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.excel.ExcelCreateDTO;
import com.wise.forms_coleta.dtos.excel.ExcelDTO;
import com.wise.forms_coleta.services.excel.ExcelSaveService;
import com.wise.forms_coleta.services.exportar_excel.GetExcelDataService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Autowired
    ExcelSaveService excelSaveService;

    @Autowired
    GetExcelDataService getExcelDataService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid ExcelCreateDTO data, UriComponentsBuilder uriBuilder){
        ExcelDTO excelDTO = excelSaveService.save(data);
        URI uri = uriBuilder.path("/excel/{id}").buildAndExpand(excelDTO.id()).toUri();
        return ResponseEntity.created(uri).body(excelDTO);
    }

    @GetMapping("{sheetName}")
    public ResponseEntity<List<List<Object>>> getPontosByExcel(@PathVariable String sheetName){
        return new ResponseEntity<>(getExcelDataService.readExcelFile(sheetName), HttpStatus.OK);
    }
}
