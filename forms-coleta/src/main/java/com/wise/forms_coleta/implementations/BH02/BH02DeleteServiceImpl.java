package com.wise.forms_coleta.implementations.BH02;

import com.wise.forms_coleta.entities.BH02;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BH02Repository;
import com.wise.forms_coleta.services.BH02.BH02DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BH02DeleteServiceImpl implements BH02DeleteService {
    @Autowired
    private BH02Repository bh02Repository;

    @Override
    public String delete(Long id){
        BH02 bh02 = bh02Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        bh02Repository.delete(bh02);
        return "Formulário deletado com sucesso!";
    }
}
