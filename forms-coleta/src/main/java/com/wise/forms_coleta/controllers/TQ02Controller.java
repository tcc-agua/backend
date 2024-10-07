package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.TQ02.TQ02CreateDTO;
import com.wise.forms_coleta.dtos.TQ02.TQ02DTO;
import com.wise.forms_coleta.dtos.TQ02.TQ02PutDTO;
import com.wise.forms_coleta.dtos.pbs.PbCreateDTO;
import com.wise.forms_coleta.dtos.pbs.PbDTO;
import com.wise.forms_coleta.services.tq02.TQ02SaveService;
import com.wise.forms_coleta.services.tq02.Tq02DeleteService;
import com.wise.forms_coleta.services.tq02.Tq02GetAllService;
import com.wise.forms_coleta.services.tq02.Tq02PutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tq02")
@Tag(name = "TQ02", description = "\"API para operações relacionadas ao ponto 'TQ02'\"")
public class TQ02Controller {

    @Autowired
    TQ02SaveService tq02SaveService;

    @Autowired
    Tq02GetAllService tq02GetAllService;

    @Autowired
    Tq02DeleteService tq02DeleteService;

    @Autowired
    Tq02PutService tq02PutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid TQ02CreateDTO data, UriComponentsBuilder uriBuilder){
        TQ02DTO tq02DTO = tq02SaveService.save(data);
        URI uri = uriBuilder.path("/tq02/{id}").buildAndExpand(tq02DTO.id()).toUri();
        return ResponseEntity.created(uri).body("TQ02 criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<TQ02DTO>> getAll(){
        return new ResponseEntity<>(tq02GetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(tq02DeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<TQ02DTO> put(@PathVariable Long id, @RequestBody @Valid TQ02PutDTO data){
        return new ResponseEntity<>(tq02PutService.put(id, data), HttpStatus.OK);
    }
}
