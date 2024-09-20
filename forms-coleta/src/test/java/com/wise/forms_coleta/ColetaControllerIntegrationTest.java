package com.wise.forms_coleta;

import com.wise.forms_coleta.dtos.coleta.ColetaCreateDTO;
import com.wise.forms_coleta.dtos.coleta.ColetaDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ColetaControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ColetaRepository coletaRepository;

    private ColetaCreateDTO coletaCreateDTO;
    private Long createdColetaId;


    @BeforeEach
    public void setup(){
        coletaCreateDTO = new ColetaCreateDTO("BOSCH", LocalDate.now(), LocalTime.of(8,0), LocalTime.of(12,0));
        Coleta coleta = new Coleta(coletaCreateDTO);
        coleta = coletaRepository.save(coleta);
        createdColetaId = coleta.getId();
    }

    @Test
    @Transactional
    public void testSaveColeta(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ColetaCreateDTO> request = new HttpEntity<>(coletaCreateDTO, headers);

        // Requisição POST
        ResponseEntity<String> response = testRestTemplate.postForEntity("/coleta", request, String.class);

        // Verificação da resposta
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Verificação da mensagem de sucesso
        assertTrue(response.getBody().contains("Coleta criada com sucesso!"));

        // Verificação da URI de retorno
        URI location = response.getHeaders().getLocation();
        assertNotNull(location);

        Long coletaId = Long.parseLong(location.getPath().split("/")[2]);
        Optional<Coleta> coletaOptional = coletaRepository.findById(coletaId);
        assertTrue(coletaOptional.isPresent());

        Coleta coletaSalva = coletaOptional.get();
        assertEquals(coletaCreateDTO.tecnico(), coletaSalva.getTecnico());
        assertEquals(coletaCreateDTO.dataColeta(), coletaSalva.getDataColeta());
        assertEquals(coletaCreateDTO.horaInicio(), coletaSalva.getHora_inicio());
        assertEquals(coletaCreateDTO.horaFim(), coletaSalva.getHora_fim());
    }

    @Test
    public void testGetColetaById(){
        ResponseEntity<ColetaDTO> response = testRestTemplate.getForEntity("/coleta/" + createdColetaId, ColetaDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdColetaId, response.getBody().id());
    }

    @Test
    public void testGetAllColetas(){
        ResponseEntity<List> response = testRestTemplate.getForEntity("/coleta", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(((List<?>) response.getBody()).isEmpty());
    }

    @Test
    public void testDeleteColeta(){
        testRestTemplate.delete("/coleta/" + createdColetaId);
        Optional<Coleta> coletaOptional = coletaRepository.findById(createdColetaId);
        assertFalse(coletaOptional.isPresent());
    }

    @Test
    public void testUpdateColeta(){
        ColetaCreateDTO updateColetaDTO = new ColetaCreateDTO("TÉCNICO", LocalDate.now(), LocalTime.of(9,0), LocalTime.of(13,0));
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ColetaCreateDTO> request = new HttpEntity<>(updateColetaDTO, headers);

        testRestTemplate.put("/coleta/" + createdColetaId, request);

        ResponseEntity<ColetaDTO> response = testRestTemplate.getForEntity("/coleta/" + createdColetaId, ColetaDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        ColetaDTO updatedColeta = response.getBody();
        assertNotNull(updatedColeta);
        assertEquals("TÉCNICO", updatedColeta.tecnico());
        assertEquals(LocalTime.of(9, 0), updatedColeta.horaInicio());
        assertEquals(LocalTime.of(13, 0), updatedColeta.horaFim());
    }

    @Test
    public void testGetByDate(){
        ResponseEntity<List> response = testRestTemplate.getForEntity("/coleta/get-by-date?startDate=" + LocalDate.now() + "&endDate=" + LocalDate.now(),
                List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        List<Map<String, Object>> coletas = (List<Map<String, Object>>) response.getBody();
        assertFalse(coletas.isEmpty());
    }
}
