package com.wise.forms_coleta.repositories;

import com.wise.forms_coleta.entities.Coleta;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ColetaRepository extends JpaRepository<Coleta, Long> {
    List<Coleta> findAllByDataColeta(LocalDate date);
}
