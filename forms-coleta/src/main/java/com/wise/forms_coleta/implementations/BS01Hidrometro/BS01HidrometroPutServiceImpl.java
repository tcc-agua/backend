package com.wise.forms_coleta.implementations.BS01Hidrometro;

import com.wise.forms_coleta.dtos.BS01Hidrometro.BS01HidrometroDTO;
import com.wise.forms_coleta.dtos.BS01Hidrometro.BS01HidrometroPutDTO;
import com.wise.forms_coleta.entities.BS01Hidrometro;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BS01HidrometroRepository;
import com.wise.forms_coleta.services.BS01Hidrometro.BS01HidrometroPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BS01HidrometroPutServiceImpl implements BS01HidrometroPutService {
    @Autowired
    private BS01HidrometroRepository bs01HidrometroRepository;

    @Override
    public BS01HidrometroDTO put(Long id, BS01HidrometroPutDTO data) {
        BS01Hidrometro bs01Hidrometro = bs01HidrometroRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        bs01Hidrometro.setVolume(data.volume());

        bs01HidrometroRepository.save(bs01Hidrometro);
        return new BS01HidrometroDTO(bs01Hidrometro);

    }
}
