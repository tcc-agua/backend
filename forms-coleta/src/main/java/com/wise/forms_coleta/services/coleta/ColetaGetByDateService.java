package com.wise.forms_coleta.services.coleta;

import com.wise.forms_coleta.entities.Coleta;

import java.time.LocalDate;
import java.util.List;

public interface ColetaGetByDateService {
    List<Coleta> getAllByDate(LocalDate date);

    List<Coleta> getAllByDateRange(LocalDate startDate, LocalDate endDate);

}
