package com.wise.forms_coleta.repositories;

import com.wise.forms_coleta.entities.Hidrometro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HidrometroRepository extends JpaRepository<Hidrometro, Long> {

    List<Hidrometro> findAllByPontoId(Long pontoId);
}
