package com.wise.forms_coleta.implementations.sensor_ph;

import com.wise.forms_coleta.dtos.sensor_ph.SensorPHDTO;
import com.wise.forms_coleta.dtos.sensor_ph.SensorPHPutDTO;
import com.wise.forms_coleta.entities.SensorPH;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.SensorPHRepository;
import com.wise.forms_coleta.services.sensor_ph.SensorPHPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorPHPutServiceImpl implements SensorPHPutService {

    @Autowired
    private SensorPHRepository sensorPHRepository;

    // Método de alterar a instância de sensor ph
    @Override
    public SensorPHDTO put(Long id, SensorPHPutDTO data) {
        // Encontrando a instância de sensor ph pelo id
        SensorPH sensorPH = sensorPHRepository.findById(id)
                .orElseThrow( () -> new GenericsNotFoundException("Formulário não encontrado!"));

        // Setando as alterações nos campos
        sensorPH.setPh(data.ph());

        // Salvando no banco as alterações
        sensorPHRepository.save(sensorPH);
        return new SensorPHDTO(sensorPH);

    }
}
