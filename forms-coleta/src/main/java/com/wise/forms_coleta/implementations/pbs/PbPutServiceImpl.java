package com.wise.forms_coleta.implementations.pbs;

import com.wise.forms_coleta.dtos.pbs.PbDTO;
import com.wise.forms_coleta.dtos.pbs.PbPutDTO;
import com.wise.forms_coleta.entities.PBs;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.PbRepository;
import com.wise.forms_coleta.services.pbs.PbPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PbPutServiceImpl implements PbPutService {
    @Autowired
    private PbRepository pbRepository;

    // Método de alterar a instância de pb
    @Override
    public PbDTO put(Long id, PbPutDTO data) {
        // Encontrando a instância de pb pelo id
        PBs pBs = pbRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        // Setando as alterações nos campos
        pBs.setPressao(data.pressao());
        pBs.setPulsos(data.pulsos());
        pBs.setNivel_agua(data.nivel_agua());
        pBs.setNivel_oleo(data.nivel_oleo());
        pBs.setVol_rem_oleo(data.vol_rem_oleo());

        // Salvando no banco as alterações
        pbRepository.save(pBs);

        return new PbDTO(pBs);
    }
}
