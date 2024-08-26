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

    @Override
    public SensorPHDTO save(SensorPHCreateDTO data) {
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        SensorPH sensorPH = new SensorPH(data);
        sensorPH.setPonto(ponto);

        coleta.getPhSet().add(sensorPH);

        sensorPHRepo.save(sensorPH);
        coleta.setHora_fim(LocalTime.now());
        coletaRepository.save(coleta);

        return new SensorPHDTO(sensorPH);
    }

}
