package com.wise.forms_coleta.services.sensor_ph;

import com.wise.forms_coleta.dtos.sensor_ph.SensorPHDTO;
import com.wise.forms_coleta.dtos.sensor_ph.SensorPHPutDTO;

public interface SensorPHPutService {
    SensorPHDTO put(Long id, SensorPHPutDTO data);
}
