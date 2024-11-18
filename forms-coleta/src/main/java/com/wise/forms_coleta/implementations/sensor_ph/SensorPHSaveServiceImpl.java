package com.wise.forms_coleta.implementations.sensor_ph;

import com.wise.forms_coleta.dtos.sensor_ph.SensorPHCreateDTO;
import com.wise.forms_coleta.dtos.sensor_ph.SensorPHDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.entities.SensorPH;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.repositories.SensorPHRepository;
import com.wise.forms_coleta.services.sensor_ph.SensorPHSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class SensorPHSaveServiceImpl implements SensorPHSaveService {

    @Autowired
    private SensorPHRepository sensorPHRepo;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    // Método de salvar nova coleta de sensor ph
    @Override
    public SensorPHDTO save(SensorPHCreateDTO data) {

        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade sensor ph
        SensorPH sensorPH = new SensorPH(data);
        // Setando o ponto
        sensorPH.setPonto(ponto);

        // Associando a instância de sensor ph criada a coleta
        coleta.getPhSet().add(sensorPH);

        // Salvando no banco a instância de sensor ph
        sensorPHRepo.save(sensorPH);
        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new SensorPHDTO(sensorPH);
    }

}
