package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.faseLivre.FaseLivreCreateDTO;
import com.wise.forms_coleta.dtos.faseLivre.FaseLivreDTO;
import com.wise.forms_coleta.services.faseLivre.FaseLivreSaveService;
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
@RequestMapping("/faselivre")
public class FaseLivreController {

    @Autowired
    FaseLivreSaveService faseLivreSaveService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid FaseLivreCreateDTO data, UriComponentsBuilder uriBuilder){
        FaseLivreDTO faseLivreDTO = faseLivreSaveService.save(data);
        URI uri = uriBuilder.path("/faselivre/{id}").buildAndExpand(faseLivreDTO.id()).toUri();

        return ResponseEntity.created(uri).body("Formulário criado com sucesso!");
    }
}
