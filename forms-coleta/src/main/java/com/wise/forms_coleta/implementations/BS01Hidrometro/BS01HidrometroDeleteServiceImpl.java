package com.wise.forms_coleta.implementations.BS01Hidrometro;

import com.wise.forms_coleta.entities.BS01Hidrometro;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BS01HidrometroRepository;
import com.wise.forms_coleta.services.BS01Hidrometro.BS01HidrometroDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BS01HidrometroDeleteServiceImpl implements BS01HidrometroDeleteService {

    @Autowired
    private BS01HidrometroRepository bs01HidrometroRepository;

    @Override
    public String delete(Long id) {
        BS01Hidrometro bs01Hidrometro  = bs01HidrometroRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        bs01HidrometroRepository.delete(bs01Hidrometro);
        return "Formulário deletado com sucesso!";
    }


}
