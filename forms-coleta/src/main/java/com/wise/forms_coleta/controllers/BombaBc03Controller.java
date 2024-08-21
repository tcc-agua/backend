package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03CreateDTO;
import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03DTO;
import com.wise.forms_coleta.services.bomba_bc03.BombaBc03SaveService;
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
@RequestMapping("/bombabc03")
public class BombaBc03Controller {

    @Autowired
    BombaBc03SaveService bombaBc03SaveService;


    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid BombaBc03CreateDTO data, UriComponentsBuilder uriBuilder){
        BombaBc03DTO bombaBc03DTO = bombaBc03SaveService.save(data);
        URI uri = uriBuilder.path("/bombabc03/{id}").buildAndExpand(bombaBc03DTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }
}
