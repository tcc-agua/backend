package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.cd.CDCreateDTO;
import com.wise.forms_coleta.dtos.cd.CDDTO;
import com.wise.forms_coleta.dtos.cd.CDPutDTO;
import com.wise.forms_coleta.services.CD.CDDeleteService;
import com.wise.forms_coleta.services.CD.CDGetAllService;
import com.wise.forms_coleta.services.CD.CDPutService;
import com.wise.forms_coleta.services.CD.CDSaveService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cd")
public class CDController {
    @Autowired
    CDSaveService cdSaveService;

    @Autowired
    CDGetAllService cdGetAllService;

    @Autowired
    CDPutService cdPutService;

    @Autowired
    CDDeleteService cdDeleteService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid CDCreateDTO data, UriComponentsBuilder uriComponentsBuilder){
        CDDTO cddto = cdSaveService.save(data);
        URI uri = uriComponentsBuilder.path("/cd/{id}").buildAndExpand(cddto.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário salvo com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<CDDTO>> getAll() {
        return new ResponseEntity<>(cdGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return  new ResponseEntity<>(cdDeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<CDDTO> put(@PathVariable Long id, @RequestBody @Valid CDPutDTO data) {
        return new ResponseEntity<>(cdPutService.put(id, data), HttpStatus.OK);
    }
}
