package com.wise.forms_coleta.controllers;


import com.wise.forms_coleta.dtos.sensor_ph.SensorPHCreateDTO;
import com.wise.forms_coleta.dtos.sensor_ph.SensorPHDTO;
import com.wise.forms_coleta.dtos.sensor_ph.SensorPHPutDTO;
import com.wise.forms_coleta.implementations.sensor_ph.SensorPHSaveServiceImpl;
import com.wise.forms_coleta.services.sensor_ph.SensorPHDeleteService;
import com.wise.forms_coleta.services.sensor_ph.SensorPHGetAllService;
import com.wise.forms_coleta.services.sensor_ph.SensorPHPutService;
import com.wise.forms_coleta.services.sensor_ph.SensorPHSaveService;
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
@RequestMapping("/sensor-ph")
public class SensorPHController {

    @Autowired
    SensorPHSaveService sensorPHSaveService;

    @Autowired
    SensorPHPutService sensorPHPutService;

    @Autowired
    SensorPHDeleteService sensorPHDeleteService;

    @Autowired
    SensorPHGetAllService sensorPHGetAllService;


    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid SensorPHCreateDTO data, UriComponentsBuilder uriBuilder) {
        SensorPHDTO sensorPHDTO = sensorPHSaveService.save(data);
        URI uri = uriBuilder.path("/sensor-ph/{id}").buildAndExpand(sensorPHDTO.id()).toUri();
        return ResponseEntity.created(uri).body("Formulário preenchido com sucesso!");
    }

    @GetMapping("/get-ph")
    public ResponseEntity<List<SensorPHDTO>> getAll() {
        return new ResponseEntity<>(sensorPHGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> delete (@PathVariable Long id) {
        return new ResponseEntity<>(sensorPHDeleteService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<SensorPHDTO> put(@PathVariable Long id, @RequestBody @Valid SensorPHPutDTO data){
        return new ResponseEntity<>(sensorPHPutService.put(id, data), HttpStatus.OK);
    }

}
