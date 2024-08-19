package com.wise.forms_coleta.implementations.sensor_ph;

import com.wise.forms_coleta.dtos.sensor_ph.SensorPHDTO;
import com.wise.forms_coleta.repositories.SensorPHRepository;
import com.wise.forms_coleta.services.sensor_ph.SensorPHGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorPHGetAllServiceImpl implements SensorPHGetAllService {

    @Autowired
    private SensorPHRepository sensorPHRepository;

    @Override
    public List<SensorPHDTO> getAll() {
        return sensorPHRepository.findAll().stream().map(SensorPHDTO::new).toList();
    }

}
