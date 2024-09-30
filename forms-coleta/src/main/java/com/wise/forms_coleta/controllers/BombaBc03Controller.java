package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03CreateDTO;
import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03DTO;
import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03PutDTO;
import com.wise.forms_coleta.services.bomba_bc03.BombaBc03DeleteService;
import com.wise.forms_coleta.services.bomba_bc03.BombaBc03GetAllService;
import com.wise.forms_coleta.services.bomba_bc03.BombaBc03PutService;
import com.wise.forms_coleta.services.bomba_bc03.BombaBc03SaveService;
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
@RequestMapping("/bombabc03")
public class BombaBc03Controller {

    @Autowired
    BombaBc03SaveService bombaBc03SaveService;

    @Autowired
    BombaBc03GetAllService bombaBc03GetAllService;

    @Autowired
    BombaBc03DeleteService bombaBc03DeleteService;

    @Autowired
    BombaBc03PutService bombaBc03PutService;


    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid BombaBc03CreateDTO data, UriComponentsBuilder uriBuilder){
        BombaBc03DTO bombaBc03DTO = bombaBc03SaveService.save(data);
        URI uri = uriBuilder.path("/bombabc03/{id}").buildAndExpand(bombaBc03DTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<BombaBc03DTO>> getAll() {
        return new ResponseEntity<>(bombaBc03GetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(bombaBc03DeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<BombaBc03DTO> put(@PathVariable Long id, @RequestBody @Valid BombaBc03PutDTO data){
        return new ResponseEntity<>(bombaBc03PutService.put(id, data), HttpStatus.OK);
    }
}
