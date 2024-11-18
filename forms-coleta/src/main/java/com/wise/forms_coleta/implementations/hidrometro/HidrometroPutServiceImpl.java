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

    // Método de alterar a instância de hidrometro
    @Override
    public HidrometroDTO put(Long id, HidrometroPutDTO data) {
        // Encontrando a instância de hidrometro pelo id
        Hidrometro hidrometro = hidrometroRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        // Setando as alterações nos campos
        hidrometro.setVolume(data.volume());
        // Salvando no banco as alterações
        hidrometroRepository.save(hidrometro);
        return new HidrometroDTO(hidrometro);
    }

}
