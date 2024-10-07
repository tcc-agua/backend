package com.wise.forms_coleta.controllers;

import com.wise.forms_coleta.dtos.notificacao.NotifCreateDTO;
import com.wise.forms_coleta.dtos.notificacao.NotifDTO;
import com.wise.forms_coleta.services.Notificacoes.NotifDeleteAllService;
import com.wise.forms_coleta.services.Notificacoes.NotifGetAllService;
import com.wise.forms_coleta.services.Notificacoes.NotifSaveService;
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
@RequestMapping("/notificacoes")
@Tag(name = "Notificações", description = "\"API para operações relacionadas as 'Notificações'\"")
public class NotificacoesController {
    @Autowired
    NotifSaveService notifSaveService;

    @Autowired
    NotifGetAllService notifGetAllService;

    @Autowired
    NotifDeleteAllService notifDeleteAllService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid NotifCreateDTO data, UriComponentsBuilder uriBuilder) {
        NotifDTO notifDTO = notifSaveService.save(data);
        URI uri = uriBuilder.path("/noticacoes/{id}").buildAndExpand(notifDTO.id()).toUri();
        return ResponseEntity.created(uri).body("Notificacao criada com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<NotifDTO>> getAll(){
    return new ResponseEntity<>(notifGetAllService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<String> deleteAll() {
        return new ResponseEntity<>(notifDeleteAllService.deleteAll(), HttpStatus.OK);
    }

}
