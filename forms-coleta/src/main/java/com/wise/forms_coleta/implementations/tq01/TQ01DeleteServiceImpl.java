package com.wise.forms_coleta.implementations.tq01;

import com.wise.forms_coleta.entities.TQ01;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.TQ01Repository;
import com.wise.forms_coleta.services.tq01.TQ01DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TQ01DeleteServiceImpl implements TQ01DeleteService {

    @Autowired
    private TQ01Repository tq01Repository;

    // Método para deletar uma instância de TQ01
    @Override
    public String delete(Long id) {
        // Pegando a instância de TQ01 pelo id
        TQ01 tq01  = tq01Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encotrado!"));

        // Deletando no banco a instância
        tq01Repository.delete(tq01);
        return "Formulário deletado com sucesso!";
    }

}
