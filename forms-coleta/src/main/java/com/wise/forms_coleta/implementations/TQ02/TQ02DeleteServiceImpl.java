package com.wise.forms_coleta.implementations.TQ02;

import com.wise.forms_coleta.entities.TQ02;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.TQ02Repository;
import com.wise.forms_coleta.services.tq02.Tq02DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TQ02DeleteServiceImpl implements Tq02DeleteService {

    @Autowired
    private TQ02Repository tq02Repository;

    @Override
    public String delete(Long id) {
        TQ02 tq02 = tq02Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        tq02Repository.delete(tq02);
        return "Formulário deletado com sucesso!";
    }
}
