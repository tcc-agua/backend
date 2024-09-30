package com.wise.forms_coleta.implementations.tq04_tq05;

import com.wise.forms_coleta.entities.Tq04Tq05;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.Tq04Tq05Repository;
import com.wise.forms_coleta.services.tq04_tq05.Tq04Tq05DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Tq04Tq05DeleteServiceImpl implements Tq04Tq05DeleteService {

    @Autowired
    private Tq04Tq05Repository tq04Tq05Repository;

    @Override
    public String delete(Long id) {
        Tq04Tq05 tq04Tq05 = tq04Tq05Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));
        tq04Tq05Repository.delete(tq04Tq05);
        return "Formulário deletado com sucesso!";
    }
}
