package com.wise.forms_coleta.implementations.horimetro;

import com.wise.forms_coleta.dtos.horimetro.HorimetroDTO;
import com.wise.forms_coleta.dtos.horimetro.HorimetroPutDTO;
import com.wise.forms_coleta.entities.Horimetro;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.HorimetroRepository;
import com.wise.forms_coleta.services.horimetro.HorimetroPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorimetroPutServiceImpl implements HorimetroPutService {
    @Autowired
    private HorimetroRepository horimetroRepository;

    // Método de alterar a instância de horimetro
    @Override
    public HorimetroDTO put(Long id, HorimetroPutDTO data) {
        // Encontrando a instância de horimetro pelo id
        Horimetro horimetro = horimetroRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));
        // Setando as alterações nos campos
        horimetro.setHorimetro(data.horimetro());

        // Salvando no banco as alterações
        horimetroRepository.save(horimetro);
        return new HorimetroDTO(horimetro);
    }
}
