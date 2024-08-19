package com.wise.forms_coleta.services.coleta;

import com.wise.forms_coleta.entities.Coleta;

import java.time.LocalDate;
import java.util.List;

public interface ColetaGetByDateService {
    List<Coleta> getALlByDate(LocalDate date);
}
