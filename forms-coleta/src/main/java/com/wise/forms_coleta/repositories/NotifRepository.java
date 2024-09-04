package com.wise.forms_coleta.repositories;

import com.wise.forms_coleta.entities.Notificacoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NotifRepository extends JpaRepository<Notificacoes, Long> {

}
