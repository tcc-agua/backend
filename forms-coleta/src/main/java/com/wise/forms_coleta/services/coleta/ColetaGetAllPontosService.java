package com.wise.forms_coleta.services.coleta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ColetaGetAllPontosService {
    Page<Map<String, Object>> getAllPontosByDate(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
