package com.wise.forms_coleta.services.hidrometro;

import com.wise.forms_coleta.dtos.hidrometro.HidrometroDTO;
import com.wise.forms_coleta.entities.Hidrometro;

import java.time.LocalDate;
import java.util.List;

public interface HidrometroFindByPontoService {
    List<Hidrometro> FindByPonto(String ponto, LocalDate startDate, LocalDate endDate);
}
