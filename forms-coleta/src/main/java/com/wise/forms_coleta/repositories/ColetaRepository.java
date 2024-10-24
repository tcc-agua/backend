package com.wise.forms_coleta.repositories;

import com.wise.forms_coleta.entities.Coleta;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ColetaRepository extends JpaRepository<Coleta, Long> {
    Page<Coleta> findAllByDataColetaBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    List<Coleta> findAllByDataColetaBetween(LocalDate startDate, LocalDate endDate);
}
