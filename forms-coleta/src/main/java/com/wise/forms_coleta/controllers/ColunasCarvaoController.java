package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoCreateDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoPutDTO;
import com.wise.forms_coleta.services.colunas_carvao.ColunasCarvaoDeleteService;
import com.wise.forms_coleta.services.colunas_carvao.ColunasCarvaoGetAllService;
import com.wise.forms_coleta.services.colunas_carvao.ColunasCarvaoPutService;
import com.wise.forms_coleta.services.colunas_carvao.ColunasCarvaoSaveService;
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
@RequestMapping("/colunas-carvao")
@Tag(name = "Colunas Carvão", description = "\"API para operações relacionadas ao ponto 'Colunas Carvão'\"")
public class ColunasCarvaoController {
    @Autowired
    ColunasCarvaoSaveService colunasCarvaoSaveService;

    @Autowired
    ColunasCarvaoGetAllService colunasCarvaoGetAllService;

    @Autowired
    ColunasCarvaoDeleteService colunasCarvaoDeleteService;

    @Autowired
    ColunasCarvaoPutService colunasCarvaoPutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid ColunasCarvaoCreateDTO data, UriComponentsBuilder uriBuilder){
        ColunasCarvaoDTO colunasCarvaoDTO = colunasCarvaoSaveService.save(data);
        URI uri = uriBuilder.path("/colunas-carvao/{id}").buildAndExpand(colunasCarvaoDTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<ColunasCarvaoDTO>> getAll(){
        return new ResponseEntity<>(colunasCarvaoGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(colunasCarvaoDeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ColunasCarvaoDTO> put(@PathVariable Long id, @RequestBody @Valid ColunasCarvaoPutDTO data){
        return new ResponseEntity<>(colunasCarvaoPutService.put(id, data), HttpStatus.OK);
    }
}
