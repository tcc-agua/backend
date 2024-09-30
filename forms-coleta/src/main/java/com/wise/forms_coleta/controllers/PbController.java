package com.wise.forms_coleta.controllers;


import com.wise.forms_coleta.dtos.pbs.PbCreateDTO;
import com.wise.forms_coleta.dtos.pbs.PbDTO;
import com.wise.forms_coleta.dtos.pbs.PbPutDTO;
import com.wise.forms_coleta.services.pbs.PbDeleteService;
import com.wise.forms_coleta.services.pbs.PbGetAllService;
import com.wise.forms_coleta.services.pbs.PbPutService;
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

    @Autowired
    PbDeleteService pbDeleteService;

    @Autowired
    PbPutService pbPutService;

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

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(pbDeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<PbDTO> put(@PathVariable Long id, @RequestBody @Valid PbPutDTO data){
        return new ResponseEntity<>(pbPutService.put(id, data), HttpStatus.OK);
    }
}
