package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.tq01.TQ01CreateDTO;
import com.wise.forms_coleta.dtos.tq01.TQ01DTO;
import com.wise.forms_coleta.dtos.tq01.TQ01PutDTO;
import com.wise.forms_coleta.services.tq01.TQ01DeleteService;
import com.wise.forms_coleta.services.tq01.TQ01GetAllService;
import com.wise.forms_coleta.services.tq01.TQ01PutService;
import com.wise.forms_coleta.services.tq01.TQ01SaveService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/TQ01")
public class TQ01Controller {
    @Autowired
    TQ01SaveService tq01SaveService;

    @Autowired
    TQ01GetAllService tq01GetAllService;

    @Autowired
    TQ01DeleteService tq01DeleteService;

    @Autowired
    TQ01PutService tq01PutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid TQ01CreateDTO data, UriComponentsBuilder uriBuilder){
        TQ01DTO tq01DTO = tq01SaveService.save(data);
        URI uri = uriBuilder.path("/TQ01/{id}").buildAndExpand(tq01DTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<TQ01DTO>> getAll() {
        return new ResponseEntity<>(tq01GetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(tq01DeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<TQ01DTO> put(@PathVariable Long id, @RequestBody @Valid TQ01PutDTO data) {
        return new ResponseEntity<>(tq01PutService.put(id, data), HttpStatus.OK);
    }



}
