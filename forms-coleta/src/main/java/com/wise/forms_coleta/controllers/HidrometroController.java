package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.hidrometro.HidrometroCreateDTO;
import com.wise.forms_coleta.dtos.hidrometro.HidrometroDTO;
import com.wise.forms_coleta.dtos.hidrometro.HidrometroPutDTO;
import com.wise.forms_coleta.entities.Hidrometro;
import com.wise.forms_coleta.services.hidrometro.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hidrometro")
@Tag(name = "Hidrometro", description = "\"API para operações relacionadas aos pontos 'Hidrometro'\"")
public class HidrometroController {
    @Autowired
    HidrometroSaveService hidrometroSaveService;

    @Autowired
    HidrometroGetAllService hidrometroGetAllService;

    @Autowired
    HidrometroDeleteService hidrometroDeleteService;

    @Autowired
    HidrometroPutService hidrometroPutService;

    @Autowired
    HidrometroFindByPontoService hidrometroFindByPontoService;

    // Endpoint de registro de coletas do hidrometro
    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid HidrometroCreateDTO data, UriComponentsBuilder uriBuilder){
        HidrometroDTO hidrometroDTO = hidrometroSaveService.save(data);
        URI uri = uriBuilder.path("/hidrometro/{id}").buildAndExpand(hidrometroDTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

    // Endpoint de listagem de todas as coletas de hidrometro
    @GetMapping
    public ResponseEntity<List<HidrometroDTO>> getAll() {
        return new ResponseEntity<>(hidrometroGetAllService.getAll(), HttpStatus.OK);
    }

    // Endpoint para deletar uma coleta de hidrometro
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(hidrometroDeleteService.delete(id), HttpStatus.OK);
    }

    // Endpoint de atualização de uma coleta de hidrometro
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<HidrometroDTO> put(@PathVariable Long id, @RequestBody @Valid HidrometroPutDTO data) {
        return new ResponseEntity<>(hidrometroPutService.put(id,data), HttpStatus.OK);
    }

    // Endpoint para pegar todas as coletas de hidrometro de acordo com um ponto, filtrando por data
    @GetMapping("/ponto/{ponto}")
    public ResponseEntity<List<Hidrometro>> getAllByPonto(@PathVariable String ponto, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return new ResponseEntity<>(hidrometroFindByPontoService.FindByPonto(ponto, startDate, endDate), HttpStatus.OK);
    }
}
