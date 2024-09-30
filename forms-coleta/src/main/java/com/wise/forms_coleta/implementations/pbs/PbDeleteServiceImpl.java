package com.wise.forms_coleta.implementations.pbs;

import com.wise.forms_coleta.entities.PBs;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.PbRepository;
import com.wise.forms_coleta.services.pbs.PbDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PbDeleteServiceImpl implements PbDeleteService {
    @Autowired
    private PbRepository pbRepository;


    @Override
    public String delete(Long id) {
        PBs pBs = pbRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        pbRepository.delete(pBs);

        return "Formulário deletado com sucesso!";
    }
}
