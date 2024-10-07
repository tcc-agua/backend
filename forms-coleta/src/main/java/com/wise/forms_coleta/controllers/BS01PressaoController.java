package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoCreateDTO;
import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoDTO;
import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoPutDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoPutDTO;
import com.wise.forms_coleta.services.BS01Pressao.BS01PressaoDeleteService;
import com.wise.forms_coleta.services.BS01Pressao.BS01PressaoGetAllService;
import com.wise.forms_coleta.services.BS01Pressao.BS01PressaoPutService;
import com.wise.forms_coleta.services.BS01Pressao.BS01PressaoSaveService;
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
@RequestMapping("/bs01-pressao")
@Tag(name = "BS01 Pressão", description = "\"API para operações relacionadas ao ponto 'BS01 Pressão'\"")
public class BS01PressaoController {

    @Autowired
    BS01PressaoDeleteService bs01PressaoDeleteService;

    @Autowired
    BS01PressaoGetAllService bs01PressaoGetAllService;

    @Autowired
    BS01PressaoSaveService bs01PressaoSaveService;

    @Autowired
    BS01PressaoPutService bs01PressaoPutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid BS01PressaoCreateDTO data, UriComponentsBuilder uriBuilder){
        BS01PressaoDTO bs01PressaoDTO = bs01PressaoSaveService.save(data);
        URI uri = uriBuilder.path("/bs01-pressao/{id}").buildAndExpand(bs01PressaoDTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<BS01PressaoDTO>> getAll(){
        return new ResponseEntity<>(bs01PressaoGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(bs01PressaoDeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<BS01PressaoDTO> put(@PathVariable Long id, @RequestBody @Valid BS01PressaoPutDTO data){
        return new ResponseEntity<>(bs01PressaoPutService.put(id, data), HttpStatus.OK);
    }

}
