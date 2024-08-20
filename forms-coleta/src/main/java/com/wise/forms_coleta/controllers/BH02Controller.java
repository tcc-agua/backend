package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.bh02.BH02CreateDTO;
import com.wise.forms_coleta.dtos.bh02.BH02DTO;
import com.wise.forms_coleta.dtos.bh02.BH02PutDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoDTO;
import com.wise.forms_coleta.dtos.colunas_carvao.ColunasCarvaoPutDTO;
import com.wise.forms_coleta.services.BH02.BH02DeleteService;
import com.wise.forms_coleta.services.BH02.BH02GetAllService;
import com.wise.forms_coleta.services.BH02.BH02PutService;
import com.wise.forms_coleta.services.BH02.BH02SaveService;
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
@RequestMapping("/bh02")
public class BH02Controller {
    @Autowired
    BH02SaveService bh02SaveService;

    @Autowired
    BH02GetAllService bh02GetAllService;

    @Autowired
    BH02DeleteService bh02DeleteService;

    @Autowired
    BH02PutService bh02PutService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid BH02CreateDTO data, UriComponentsBuilder uriBuilder) {
        BH02DTO bh02DTO = bh02SaveService.save(data);
        URI uri = uriBuilder.path("bh02/{id}").buildAndExpand(bh02DTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<BH02DTO>> getAll() {
        return new ResponseEntity<>(bh02GetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(bh02DeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<BH02DTO> put(@PathVariable Long id, @RequestBody @Valid BH02PutDTO data)
    {
        return new ResponseEntity<>(bh02PutService.put(id, data), HttpStatus.OK);
    }
}
