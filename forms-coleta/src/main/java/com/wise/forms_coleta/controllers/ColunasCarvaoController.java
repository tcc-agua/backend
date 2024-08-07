package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoCreateDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.services.colunas_carvao.ColunasCarvaoSaveService;
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
@RequestMapping("/colunas-carvao")
public class ColunasCarvaoController {
    @Autowired
    ColunasCarvaoSaveService colunasCarvaoSaveService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid ColunasCarvaoCreateDTO data, UriComponentsBuilder uriBuilder){
        ColunasCarvaoDTO colunasCarvaoDTO = colunasCarvaoSaveService.save(data);
        URI uri = uriBuilder.path("/colunas-carvao/{id}").buildAndExpand(colunasCarvaoDTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário criado com sucesso!");
    }
}
