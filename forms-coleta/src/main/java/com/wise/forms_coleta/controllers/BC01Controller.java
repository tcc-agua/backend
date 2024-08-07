package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.bc01.BC01CreateDTO;
import com.wise.forms_coleta.dtos.bc01.BC01DTO;
import com.wise.forms_coleta.services.BC01.BC01SaveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/BC01")
public class BC01Controller {

    @Autowired
    BC01SaveService bc01SaveService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid BC01CreateDTO data, UriComponentsBuilder uriBuilder) {
        BC01DTO bc01DTO = bc01SaveService.save(data);
        URI uri = uriBuilder.path("/BC01/{id}").buildAndExpand(bc01DTO.id()).toUri();
        return ResponseEntity.created(uri).body("Coleta salva com sucesso!");
    }

}
