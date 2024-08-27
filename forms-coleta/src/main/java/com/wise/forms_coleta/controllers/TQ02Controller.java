package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.TQ02.TQ02CreateDTO;
import com.wise.forms_coleta.dtos.TQ02.TQ02DTO;
import com.wise.forms_coleta.dtos.pbs.PbCreateDTO;
import com.wise.forms_coleta.dtos.pbs.PbDTO;
import com.wise.forms_coleta.services.tq02.TQ02SaveService;
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
@RequestMapping("/tq02")
public class TQ02Controller {

    @Autowired
    TQ02SaveService tq02SaveService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid TQ02CreateDTO data, UriComponentsBuilder uriBuilder){
        TQ02DTO tq02DTO = tq02SaveService.save(data);
        URI uri = uriBuilder.path("/tq02/{id}").buildAndExpand(tq02DTO.id()).toUri();
        return ResponseEntity.created(uri).body("TQ02 criado com sucesso!");
    }
}
