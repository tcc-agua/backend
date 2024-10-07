package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.BC06.BC06CreateDTO;
import com.wise.forms_coleta.dtos.BC06.BC06DTO;
import com.wise.forms_coleta.dtos.BC06.BC06PutDTO;
import com.wise.forms_coleta.services.BC06.BC06DeleteService;
import com.wise.forms_coleta.services.BC06.BC06GetAllService;
import com.wise.forms_coleta.services.BC06.BC06PutService;
import com.wise.forms_coleta.services.BC06.BC06SaveService;
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
@RequestMapping("/bc06")
@Tag(name = "BC06", description = "\"API para operações relacionadas ao ponto 'BC06'\"")
public class BC06Controller {
    @Autowired
    BC06SaveService bc06SaveService;

    @Autowired
    BC06GetAllService bc06GetAllService;

    @Autowired
    BC06DeleteService bc06DeleteService;

    @Autowired
    BC06PutService bc06PutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid BC06CreateDTO data, UriComponentsBuilder uriBuilder){
        BC06DTO bc06DTO = bc06SaveService.save(data);
        URI uri = uriBuilder.path("/bc06/{id}").buildAndExpand(bc06DTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário criado com sucesso!");
    }
    @GetMapping
    public ResponseEntity<List<BC06DTO>> getAll(){
        return new ResponseEntity<>(bc06GetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(bc06DeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<BC06DTO> put(@PathVariable Long id, @RequestBody @Valid BC06PutDTO data){
        return new ResponseEntity<>(bc06PutService.put(id, data), HttpStatus.OK);
    }
}
