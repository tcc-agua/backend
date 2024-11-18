package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.excel.ExcelCreateDTO;
import com.wise.forms_coleta.dtos.excel.ExcelDTO;
import com.wise.forms_coleta.services.excel.ExcelSaveService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/excel")
@Tag(name = "Excel", description = "\"API para operações relacionadas ao Excel\"")
public class ExcelController {
    @Autowired
    ExcelSaveService excelSaveService;

    // Endpoint de registro de excel
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid ExcelCreateDTO data, UriComponentsBuilder uriBuilder){
        ExcelDTO excelDTO = excelSaveService.save(data);
        URI uri = uriBuilder.path("/excel/{id}").buildAndExpand(excelDTO.id()).toUri();
        return ResponseEntity.created(uri).body(excelDTO);
    }

}
