package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.hidrometro.HidrometroCreateDTO;
import com.wise.forms_coleta.dtos.hidrometro.HidrometroDTO;
import com.wise.forms_coleta.dtos.hidrometro.HidrometroPutDTO;
import com.wise.forms_coleta.services.hidrometro.HidrometroDeleteService;
import com.wise.forms_coleta.services.hidrometro.HidrometroGetAllService;
import com.wise.forms_coleta.services.hidrometro.HidrometroPutService;
import com.wise.forms_coleta.services.hidrometro.HidrometroSaveService;
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
@RequestMapping("/hidrometro")
public class HidrometroController {
    @Autowired
    HidrometroSaveService hidrometroSaveService;

    @Autowired
    HidrometroGetAllService hidrometroGetAllService;

    @Autowired
    HidrometroDeleteService hidrometroDeleteService;

    @Autowired
    HidrometroPutService hidrometroPutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid HidrometroCreateDTO data, UriComponentsBuilder uriBuilder){
        HidrometroDTO hidrometroDTO = hidrometroSaveService.save(data);
        URI uri = uriBuilder.path("/hidrometro/{id}").buildAndExpand(hidrometroDTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<HidrometroDTO>> getAll() {
        return new ResponseEntity<>(hidrometroGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(hidrometroDeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<HidrometroDTO> put(@PathVariable Long id, @RequestBody @Valid HidrometroPutDTO data) {
        return new ResponseEntity<>(hidrometroPutService.put(id,data), HttpStatus.OK);
    }
}
