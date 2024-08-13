package com.wise.forms_coleta.repositories;

import com.wise.forms_coleta.entities.Excel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExcelRepository extends JpaRepository<Excel, Long> {
    Optional<Excel> findByNome (String nome);
}
