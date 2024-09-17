package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.coleta.ColetaCreateDTO;
import com.wise.forms_coleta.dtos.coleta.ColetaDTO;
import com.wise.forms_coleta.dtos.coleta.ColetaGetByDateDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.services.coleta.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coleta")
public class ColetaController {
    @Autowired
    ColetaSaveService coletaSaveService;

    @Autowired
    ColetaGetByIdService coletaGetByIdService;

    @Autowired
    ColetaGetAllService coletaGetAllService;

    @Autowired
    ColetaDeleteService coletaDeleteService;

    @Autowired
    ColetaPutService coletaPutService;

    @Autowired
    ColetaGetAllPontosService coletaGetAllPontosService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid ColetaCreateDTO data, UriComponentsBuilder uriBuilder){
        ColetaDTO coletaDTO = coletaSaveService.save(data);
        URI uri = uriBuilder.path("/coleta/{id}").buildAndExpand(coletaDTO.id()).toUri();
        return ResponseEntity.created(uri).body("Coleta criada com sucesso!");
    }

    @GetMapping("{id}")
    public ResponseEntity<ColetaDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(coletaGetByIdService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ColetaDTO>> getAll(){
        return new ResponseEntity<>(coletaGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(coletaDeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ColetaDTO> put(@PathVariable Long id, @RequestBody @Valid ColetaCreateDTO data){
        return new ResponseEntity<>(coletaPutService.put(id, data), HttpStatus.OK);
    }

    @GetMapping("/get-by-date")
    public ResponseEntity<List<Map<String, Object>>> getByDate(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        // Log para verificar as datas recebidas
        System.out.println("Start Date: " + startDate.getClass());
        System.out.println("End Date: " + endDate.getClass());

        // Se endDate é nulo, considera-se o mesmo valor que startDate para um intervalo de uma única data.
        List<Map<String, Object>> pontosColeta = coletaGetAllPontosService.getAllPontosByDate(startDate, endDate != null ? endDate : startDate);

        return new ResponseEntity<>(pontosColeta, HttpStatus.OK);
    }

}
