package com.wise.forms_coleta.repositories;

import com.wise.forms_coleta.entities.ColunasCarvao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColunasCarvaoRepository extends JpaRepository<ColunasCarvao, Long> {
}
