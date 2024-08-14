package com.wise.forms_coleta.implementations.CD;

import com.wise.forms_coleta.entities.CD;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.CDRepository;
import com.wise.forms_coleta.services.CD.CDDeleteService;
import org.springframework.beans.factory.annotation.Autowired;

public class CDDeleteServiceImpl implements CDDeleteService {

    @Autowired
    private CDRepository cdRepo;

    @Override
    public String delete(Long id) {
        CD cd = cdRepo.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado! "));

        cdRepo.delete(cd);
        return "Formulário deletado com sucesso!";
    }
}
