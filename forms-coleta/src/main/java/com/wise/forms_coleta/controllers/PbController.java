package com.wise.forms_coleta.controllers;


import com.wise.forms_coleta.dtos.pbs.PbCreateDTO;
import com.wise.forms_coleta.dtos.pbs.PbDTO;
import com.wise.forms_coleta.services.pbs.PbSaveService;
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
@RequestMapping("/pb")
public class PbController {

    @Autowired
    PbSaveService pbSaveService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid PbCreateDTO data, UriComponentsBuilder uriBuilder){
        PbDTO pbDTO = pbSaveService.save(data);
        URI uri = uriBuilder.path("/pb/{id}").buildAndExpand(pbDTO.id()).toUri();
        return ResponseEntity.created(uri).body("pb criado com sucesso!");
    }
}
