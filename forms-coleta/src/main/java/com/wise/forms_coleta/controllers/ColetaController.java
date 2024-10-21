package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.coleta.ColetaCreateDTO;
import com.wise.forms_coleta.dtos.coleta.ColetaDTO;
import com.wise.forms_coleta.services.coleta.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@Tag(name = "Coleta", description = "\"API para operações relacionadas a coletas\"")
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
    ColetaPatchService coletaPutService;

    @Autowired
    ColetaGetAllPontosService coletaGetAllPontosService;

    @Autowired
    ColetaGetUltimaService coletaGetUltimaService;

    @PostMapping
    @Transactional
    public ResponseEntity<ColetaDTO> save(@RequestBody @Valid ColetaCreateDTO data, UriComponentsBuilder uriBuilder){
        ColetaDTO coletaDTO = coletaSaveService.save(data);
        URI uri = uriBuilder.path("/coleta/{id}").buildAndExpand(coletaDTO.id()).toUri();
        return ResponseEntity.created(uri).body(coletaDTO);
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

    @PatchMapping("{id}")
    @Transactional
    public ResponseEntity<ColetaDTO> patch(@PathVariable Long id, @RequestBody @Valid ColetaCreateDTO data){
        return new ResponseEntity<>(coletaPutService.patch(id, data), HttpStatus.OK);
    }

    @GetMapping("/get-by-date")
    public ResponseEntity<Page<Map<String, Object>>> getByDate(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);

        // Se endDate é nulo, considera-se o mesmo valor que startDate para um intervalo de uma única data.
        Page<Map<String, Object>> pontosColeta = coletaGetAllPontosService.getAllPontosByDate(startDate,
                endDate != null ? endDate : startDate, pageRequest);

        System.out.println(pontosColeta);

        return new ResponseEntity<>(pontosColeta, HttpStatus.OK);
    }

    @GetMapping("coleta_ultima")
    public ResponseEntity<ColetaDTO> getLast(){
        return new ResponseEntity<>(coletaGetUltimaService.getLast(), HttpStatus.OK);
    }

}
