package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.BS01Hidrometro.BS01HidrometroCreateDTO;
import com.wise.forms_coleta.dtos.BS01Hidrometro.BS01HidrometroDTO;
import com.wise.forms_coleta.dtos.BS01Hidrometro.BS01HidrometroPutDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoPutDTO;
import com.wise.forms_coleta.services.BS01Hidrometro.BS01HidrometroDeleteService;
import com.wise.forms_coleta.services.BS01Hidrometro.BS01HidrometroGetAllService;
import com.wise.forms_coleta.services.BS01Hidrometro.BS01HidrometroPutService;
import com.wise.forms_coleta.services.BS01Hidrometro.BS01HidrometroSaveService;
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
@RequestMapping("/bs01-hidrometro")
@Tag(name = "BS01 Hidrometro", description = "\"API para operações relacionadas ao ponto 'BS01 Hidrometro'\"")
public class BS01HidrometroController {
    @Autowired
    BS01HidrometroDeleteService bs01HidrometroDeleteService;

    @Autowired
    BS01HidrometroPutService bs01HidrometroPutService;

    @Autowired
    BS01HidrometroSaveService bs01HidrometroSaveService;

    @Autowired
    BS01HidrometroGetAllService bs01HidrometroGetAllService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid BS01HidrometroCreateDTO data, UriComponentsBuilder uriBuilder){
        BS01HidrometroDTO bs01HidrometroDTO = bs01HidrometroSaveService.save(data);
        URI uri = uriBuilder.path("/bs01-hidrometro/{id}").buildAndExpand(bs01HidrometroDTO.id()).toUri();

        return  ResponseEntity.created(uri).body("Formulário salvo com sucesso!");

    }

    @GetMapping
    public ResponseEntity<List<BS01HidrometroDTO>> getAll(){
        return new ResponseEntity<>(bs01HidrometroGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(bs01HidrometroDeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<BS01HidrometroDTO> put(@PathVariable Long id, @RequestBody @Valid BS01HidrometroPutDTO data){
        return new ResponseEntity<>(bs01HidrometroPutService.put(id, data), HttpStatus.OK);
    }
}
