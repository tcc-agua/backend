package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.ponto.PontoCreateDTO;
import com.wise.forms_coleta.dtos.ponto.PontoDTO;
import com.wise.forms_coleta.dtos.ponto.PontoPutDTO;
import com.wise.forms_coleta.services.ponto.*;
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
@RequestMapping("/ponto")
public class PontoController {

    @Autowired
    PontoSaveService pontoSaveService;

    @Autowired
    PontoGetByNameService pontoGetByNameService;

    @Autowired
    PontoGetAllService pontoGetAllService;

    @Autowired
    PontoDeleteService pontoDeleteService;

    @Autowired
    PontoPutService pontoPutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid PontoCreateDTO data, UriComponentsBuilder uriBuilder){
        PontoDTO pontoDTO = pontoSaveService.save(data);
        URI uri = uriBuilder.path("/ponto/{id}").buildAndExpand(pontoDTO.id()).toUri();
        return ResponseEntity.created(uri).body("Ponto criado com sucesso!");
    }

    @GetMapping("{name}")
    public ResponseEntity<PontoDTO> getByName(@PathVariable String name){
        return new ResponseEntity<>(pontoGetByNameService.getPointByName(name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PontoDTO>> getAll(){
        return new ResponseEntity<>(pontoGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{name}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable String name){
        return new ResponseEntity<>(pontoDeleteService.delete(name), HttpStatus.OK);
    }

    @PutMapping("{name}")
    @Transactional
    public ResponseEntity<PontoDTO> put(@PathVariable String name, @RequestBody @Valid PontoPutDTO data){
        return new ResponseEntity<>(pontoPutService.put(name, data), HttpStatus.OK);
    }
}
