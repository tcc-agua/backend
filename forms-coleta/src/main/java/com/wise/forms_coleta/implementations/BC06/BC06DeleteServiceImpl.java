package com.wise.forms_coleta.implementations.BC06;

import com.wise.forms_coleta.entities.BC06;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BC06Repository;
import com.wise.forms_coleta.services.BC06.BC06DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BC06DeleteServiceImpl implements BC06DeleteService {
    @Autowired
    private BC06Repository bc06Repository;

    @Override
    public String delete(Long id) {
        BC06 bc06 = bc06Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));
        bc06Repository.delete(bc06);
        return "Formulário deletado com sucesso!";
    }
}
