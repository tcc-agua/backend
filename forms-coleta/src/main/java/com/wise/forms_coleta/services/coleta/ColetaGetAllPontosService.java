package com.wise.forms_coleta.services.coleta;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ColetaGetAllPontosService {
    List<Map<String, Object>> getAllPontosByDate(LocalDate startDate, LocalDate endDate);
}
