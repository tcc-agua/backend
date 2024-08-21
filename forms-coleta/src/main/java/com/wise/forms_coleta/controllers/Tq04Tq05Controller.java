package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoCreateDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05CreateDTO;
import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05DTO;
import com.wise.forms_coleta.services.tq04_tq05.Tq04Tq05SaveService;
import jakarta.transaction.Transactional;
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
@RequestMapping("/tq04tq05")
public class Tq04Tq05Controller {

    @Autowired
    Tq04Tq05SaveService tq04Tq05SaveService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid Tq04Tq05CreateDTO data, UriComponentsBuilder uriBuilder){
        Tq04Tq05DTO tq04Tq05DTO = tq04Tq05SaveService.save(data);
        URI uri = uriBuilder.path("/tq04tq05/{id}").buildAndExpand(tq04Tq05DTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

}
