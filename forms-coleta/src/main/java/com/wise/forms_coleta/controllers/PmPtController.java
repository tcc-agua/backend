package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.PmPt.PmPtCreateDTO;
import com.wise.forms_coleta.dtos.PmPt.PmPtDTO;
import com.wise.forms_coleta.services.pmpt.PmPtSaveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pmpt")
public class PmPtController {

    @Autowired
    PmPtSaveService pmPtSaveService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid PmPtCreateDTO data, UriComponentsBuilder uriBuilder){
        PmPtDTO pmPtDTO = pmPtSaveService.save(data);
        URI uri = uriBuilder.path("/pmpt/{id}").buildAndExpand(pmPtDTO.id()).toUri();
        return ResponseEntity.created(uri).body("PmPt criado com sucesso!");
    }
}
