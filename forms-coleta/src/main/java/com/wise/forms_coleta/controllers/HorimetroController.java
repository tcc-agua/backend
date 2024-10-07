package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.horimetro.HorimetroCreateDTO;
import com.wise.forms_coleta.dtos.horimetro.HorimetroDTO;
import com.wise.forms_coleta.dtos.horimetro.HorimetroPutDTO;
import com.wise.forms_coleta.services.horimetro.HorimetroDeleteService;
import com.wise.forms_coleta.services.horimetro.HorimetroGetAllService;
import com.wise.forms_coleta.services.horimetro.HorimetroPutService;
import com.wise.forms_coleta.services.horimetro.HorimetroSaveService;
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
@RequestMapping("/horimetro")
@Tag(name = "Horimetro", description = "\"API para operações relacionadas aos pontos 'Horimetro'\"")
public class HorimetroController {
    @Autowired
    HorimetroSaveService horimetroSaveService;

    @Autowired
    HorimetroGetAllService horimetroGetAllService;

    @Autowired
    HorimetroDeleteService horimetroDeleteService;

    @Autowired
    HorimetroPutService horimetroPutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid HorimetroCreateDTO data, UriComponentsBuilder uriBuilder){
        HorimetroDTO horimetroDTO = horimetroSaveService.save(data);
        URI uri = uriBuilder.path("/horimetro/{id}").buildAndExpand(horimetroDTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<HorimetroDTO>> getAll(){
        return new ResponseEntity<>(horimetroGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(horimetroDeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<HorimetroDTO> put(@PathVariable Long id, @RequestBody @Valid HorimetroPutDTO data){
        return new ResponseEntity<>(horimetroPutService.put(id, data), HttpStatus.OK);
    }
}
