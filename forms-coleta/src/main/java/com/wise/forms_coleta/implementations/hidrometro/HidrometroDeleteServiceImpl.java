package com.wise.forms_coleta.implementations.hidrometro;

import com.wise.forms_coleta.entities.Hidrometro;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.HidrometroRepository;
import com.wise.forms_coleta.services.hidrometro.HidrometroDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HidrometroDeleteServiceImpl implements HidrometroDeleteService {

    @Autowired
    private HidrometroRepository hidrometroRepository;

    @Override
    public String delete(Long id) {
        Hidrometro hidrometro = hidrometroRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));
        hidrometroRepository.delete(hidrometro);
        return "Formulário deletado com sucesso!";
    }
}
