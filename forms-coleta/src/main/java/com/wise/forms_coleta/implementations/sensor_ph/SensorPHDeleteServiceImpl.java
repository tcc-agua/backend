package com.wise.forms_coleta.implementations.sensor_ph;

import com.wise.forms_coleta.entities.SensorPH;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.SensorPHRepository;
import com.wise.forms_coleta.services.sensor_ph.SensorPHDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorPHDeleteServiceImpl implements SensorPHDeleteService {

    @Autowired
    private SensorPHRepository sensorPHRepository;

    @Override
    public String delete(Long id) {
        SensorPH sensorPH = sensorPHRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));


        sensorPHRepository.delete(sensorPH);
        return "Formulário deletado com sucesso!";
    }


}
