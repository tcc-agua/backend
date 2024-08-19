package com.wise.forms_coleta.services.sensor_ph;

import com.wise.forms_coleta.dtos.sensor_ph.SensorPHCreateDTO;
import com.wise.forms_coleta.dtos.sensor_ph.SensorPHDTO;

public interface SensorPHSaveService {
    SensorPHDTO save(SensorPHCreateDTO data);
}
