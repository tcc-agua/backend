package com.wise.forms_coleta.controllers;


import com.wise.forms_coleta.dtos.pbs.PbCreateDTO;
import com.wise.forms_coleta.dtos.pbs.PbDTO;
import com.wise.forms_coleta.services.pbs.PbGetAllService;
import com.wise.forms_coleta.services.pbs.PbSaveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pb")
public class PbController {

    @Autowired
    PbSaveService pbSaveService;

    @Autowired
    PbGetAllService pbGetAllService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid PbCreateDTO data, UriComponentsBuilder uriBuilder){
        PbDTO pbDTO = pbSaveService.save(data);
        URI uri = uriBuilder.path("/pb/{id}").buildAndExpand(pbDTO.id()).toUri();
        return ResponseEntity.created(uri).body("pb criado com sucesso!");
    }

    @GetMapping("/get")
    public ResponseEntity<List<PbDTO>> getAll() {
        return new ResponseEntity<>(pbGetAllService.getAll(), HttpStatus.OK);
    }
}
