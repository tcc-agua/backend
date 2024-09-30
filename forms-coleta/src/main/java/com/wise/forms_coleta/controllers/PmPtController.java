package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.PmPt.PmPtCreateDTO;
import com.wise.forms_coleta.dtos.PmPt.PmPtDTO;
import com.wise.forms_coleta.dtos.PmPt.PmPtPutDTO;
import com.wise.forms_coleta.services.pmpt.PmPtDeleteService;
import com.wise.forms_coleta.services.pmpt.PmPtGetAllService;
import com.wise.forms_coleta.services.pmpt.PmPtPutService;
import com.wise.forms_coleta.services.pmpt.PmPtSaveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pmpt")
public class PmPtController {

    @Autowired
    PmPtSaveService pmPtSaveService;

    @Autowired
    PmPtGetAllService pmPtGetAllService;

    @Autowired
    PmPtDeleteService pmPtDeleteService;

    @Autowired
    PmPtPutService pmPtPutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid PmPtCreateDTO data, UriComponentsBuilder uriBuilder){
        PmPtDTO pmPtDTO = pmPtSaveService.save(data);
        URI uri = uriBuilder.path("/pmpt/{id}").buildAndExpand(pmPtDTO.id()).toUri();
        return ResponseEntity.created(uri).body("PmPt criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<PmPtDTO>> getAll(){
        return new ResponseEntity<>(pmPtGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(pmPtDeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<PmPtDTO> put(@PathVariable Long id, @RequestBody @Valid PmPtPutDTO data){
        return new ResponseEntity<>(pmPtPutService.put(id, data), HttpStatus.OK);
    }
}
