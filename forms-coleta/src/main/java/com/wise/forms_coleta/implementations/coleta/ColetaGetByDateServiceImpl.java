package com.wise.forms_coleta.implementations.coleta;

import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.services.coleta.ColetaGetByDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ColetaGetByDateServiceImpl implements ColetaGetByDateService {
    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public List<Coleta> getAllByDate(LocalDate date) {
        return coletaRepository.findAllByDataColeta(date);
    }

    @Override
    public List<Coleta> getAllByDateRange(LocalDate startDate, LocalDate endDate) {
        return coletaRepository.findAllByDataColetaBetween(startDate, endDate);
    }
}
