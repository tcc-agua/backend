package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoCreateDTO;
import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoDTO;
import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoPutDTO;
import com.wise.forms_coleta.services.filtro_cartucho.FiltroCartuchoDeleteService;
import com.wise.forms_coleta.services.filtro_cartucho.FiltroCartuchoGetAllService;
import com.wise.forms_coleta.services.filtro_cartucho.FiltroCartuchoPutService;
import com.wise.forms_coleta.services.filtro_cartucho.FiltroCartuchoSaveService;
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
@RequestMapping("/filtro_cartucho")
public class FiltroCartuchoController {
    @Autowired
    FiltroCartuchoSaveService filtroCartuchoSaveService;

    @Autowired
    FiltroCartuchoGetAllService filtroCartuchoGetAllService;

    @Autowired
    FiltroCartuchoDeleteService filtroCartuchoDeleteService;

    @Autowired
    FiltroCartuchoPutService filtroCartuchoPutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid FiltroCartuchoCreateDTO data, UriComponentsBuilder uriBuilder){
        FiltroCartuchoDTO filtroCartuchoDTO = filtroCartuchoSaveService.save(data);
        URI uri = uriBuilder.path("/filtro_cartucho/{id}").buildAndExpand(filtroCartuchoDTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<FiltroCartuchoDTO>> getAll(){
        return new ResponseEntity<>(filtroCartuchoGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(filtroCartuchoDeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<FiltroCartuchoDTO> put(@PathVariable Long id, @RequestBody @Valid FiltroCartuchoPutDTO data){
        return new ResponseEntity<>(filtroCartuchoPutService.put(id, data), HttpStatus.OK);
    }
}
