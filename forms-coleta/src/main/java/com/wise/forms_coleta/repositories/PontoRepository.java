package com.wise.forms_coleta.repositories;

import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.entities.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PontoRepository extends JpaRepository<Ponto, Long> {
    Optional<Ponto> findByNome(String name);
}
