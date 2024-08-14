package com.wise.forms_coleta.implementations.horimetro;

import com.wise.forms_coleta.entities.Horimetro;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.HorimetroRepository;
import com.wise.forms_coleta.services.horimetro.HorimetroDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorimetroDeleteServiceImpl implements HorimetroDeleteService {
    @Autowired
    private HorimetroRepository horimetroRepository;

    @Override
    public String delete(Long id) {
        Horimetro horimetro = horimetroRepository.findById(id)
                .orElseThrow(()-> new GenericsNotFoundException("Formulário não encontrado!"));
        horimetroRepository.delete(horimetro);
        return "Formulário deletado com sucesso!";
    }
}
