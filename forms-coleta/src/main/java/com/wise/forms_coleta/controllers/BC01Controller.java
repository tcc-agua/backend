package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.bc01.BC01CreateDTO;
import com.wise.forms_coleta.dtos.bc01.BC01DTO;
import com.wise.forms_coleta.dtos.bc01.BC01PutDTO;
import com.wise.forms_coleta.services.BC01.BC01DeleteService;
import com.wise.forms_coleta.services.BC01.BC01GetAllService;
import com.wise.forms_coleta.services.BC01.BC01PutService;
import com.wise.forms_coleta.services.BC01.BC01SaveService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bc01")
public class BC01Controller {

    @Autowired
    BC01SaveService bc01SaveService;

    @Autowired
    BC01GetAllService bc01GetAllService;

    @Autowired
    BC01DeleteService bc01DeleteService;

    @Autowired
    BC01PutService bc01PutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid BC01CreateDTO data, UriComponentsBuilder uriBuilder) {
        BC01DTO bc01DTO = bc01SaveService.save(data);
        URI uri = uriBuilder.path("/BC01/{id}").buildAndExpand(bc01DTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<BC01DTO>> getAll() {
        return new ResponseEntity<>(bc01GetAllService.getAll(), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(bc01DeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<BC01DTO> put(@PathVariable Long id, @RequestBody @Valid BC01PutDTO data){
        return new ResponseEntity<>(bc01PutService.put(id, data), HttpStatus.OK);
    }


}
