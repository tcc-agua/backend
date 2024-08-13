package com.wise.forms_coleta.implementations.BC01;

import com.wise.forms_coleta.entities.BC01;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BC01Repository;
import com.wise.forms_coleta.services.BC01.BC01DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BC01DeleteServiceImpl implements BC01DeleteService {

    @Autowired
    private BC01Repository bc01repo;

    @Override
    public String delete(Long id) {
        BC01 bc01 = bc01repo.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        bc01repo.delete(bc01);
        return "Fomulário deletado com sucesso!";
    }
}
