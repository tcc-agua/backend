package com.wise.forms_coleta.implementations.hidrometro;

import com.wise.forms_coleta.dtos.hidrometro.HidrometroDTO;
import com.wise.forms_coleta.dtos.hidrometro.HidrometroPutDTO;
import com.wise.forms_coleta.entities.Hidrometro;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.HidrometroRepository;
import com.wise.forms_coleta.services.hidrometro.HidrometroPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HidrometroPutServiceImpl implements HidrometroPutService {
    @Autowired
    private HidrometroRepository hidrometroRepository;

    @Override
    public HidrometroDTO put(Long id, HidrometroPutDTO data) {
        Hidrometro hidrometro = hidrometroRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        hidrometro.setVolume(data.volume());
        hidrometroRepository.save(hidrometro);
        return new HidrometroDTO(hidrometro);
    }

}
