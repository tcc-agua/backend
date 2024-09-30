package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoCreateDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05CreateDTO;
import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05DTO;
import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05PutDTO;
import com.wise.forms_coleta.entities.Tq04Tq05;
import com.wise.forms_coleta.services.tq04_tq05.Tq04Tq05DeleteService;
import com.wise.forms_coleta.services.tq04_tq05.Tq04Tq05GetAllService;
import com.wise.forms_coleta.services.tq04_tq05.Tq04Tq05PutService;
import com.wise.forms_coleta.services.tq04_tq05.Tq04Tq05SaveService;
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
@RequestMapping("/tq04tq05")
public class Tq04Tq05Controller {

    @Autowired
    Tq04Tq05SaveService tq04Tq05SaveService;

    @Autowired
    Tq04Tq05GetAllService tq04Tq05GetAllService;

    @Autowired
    Tq04Tq05DeleteService tq04Tq05DeleteService;

    @Autowired
    Tq04Tq05PutService tq04Tq05PutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid Tq04Tq05CreateDTO data, UriComponentsBuilder uriBuilder){
        Tq04Tq05DTO tq04Tq05DTO = tq04Tq05SaveService.save(data);
        URI uri = uriBuilder.path("/tq04tq05/{id}").buildAndExpand(tq04Tq05DTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Tq04Tq05DTO>> getAll(){
        return new ResponseEntity<>(tq04Tq05GetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(tq04Tq05DeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Tq04Tq05DTO> put(@PathVariable Long id, @RequestBody @Valid Tq04Tq05PutDTO data){
        return new ResponseEntity<>(tq04Tq05PutService.put(id, data), HttpStatus.OK);
    }

}
