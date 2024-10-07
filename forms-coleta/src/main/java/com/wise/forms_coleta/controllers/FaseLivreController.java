package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.faseLivre.FaseLivreCreateDTO;
import com.wise.forms_coleta.dtos.faseLivre.FaseLivreDTO;
import com.wise.forms_coleta.dtos.faseLivre.FaseLivrePutDTO;
import com.wise.forms_coleta.entities.FaseLivre;
import com.wise.forms_coleta.services.faseLivre.FaseLivreDeleteService;
import com.wise.forms_coleta.services.faseLivre.FaseLivreGetAllService;
import com.wise.forms_coleta.services.faseLivre.FaseLivrePutService;
import com.wise.forms_coleta.services.faseLivre.FaseLivreSaveService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/faselivre")
@Tag(name = "Fase Livre", description = "\"API para operações relacionadas ao ponto 'Fase Livre'\"")
public class FaseLivreController {

    @Autowired
    FaseLivreSaveService faseLivreSaveService;

    @Autowired
    FaseLivreGetAllService faseLivreGetAllService;

    @Autowired
    FaseLivreDeleteService faseLivreDeleteService;

    @Autowired
    FaseLivrePutService faseLivrePutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid FaseLivreCreateDTO data, UriComponentsBuilder uriBuilder){
        FaseLivreDTO faseLivreDTO = faseLivreSaveService.save(data);
        URI uri = uriBuilder.path("/faselivre/{id}").buildAndExpand(faseLivreDTO.id()).toUri();

        return ResponseEntity.created(uri).body("Formulário criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<FaseLivreDTO>> getAll(){
        return new ResponseEntity<>(faseLivreGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(faseLivreDeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<FaseLivreDTO> put(@PathVariable Long id, @RequestBody @Valid FaseLivrePutDTO data){
        return new ResponseEntity<>(faseLivrePutService.put(id, data), HttpStatus.OK);
    }

}
