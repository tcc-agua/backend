package com.wise.forms_coleta.implementations.filtro_cartucho;

import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoDTO;
import com.wise.forms_coleta.dtos.filtro_cartucho.FiltroCartuchoPutDTO;
import com.wise.forms_coleta.entities.FiltroCartucho;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.FiltroCartuchoRepository;
import com.wise.forms_coleta.services.filtro_cartucho.FiltroCartuchoPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FiltroCartuchoPutServiceImpl implements FiltroCartuchoPutService {
    @Autowired
    private FiltroCartuchoRepository filtroCartuchoRepository;

    // Método de alterar a instância de filtro cartucho
    @Override
    public FiltroCartuchoDTO put(Long id, FiltroCartuchoPutDTO data) {
        // Encontrando a instância de filtro cartucho pelo id
        FiltroCartucho filtroCartucho = filtroCartuchoRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        // Setando as alterações nos campos
        filtroCartucho.setPressao_entrada(data.pressao_entrada());
        filtroCartucho.setPressao_saida(data.pressao_saida());

        // Salvando no banco as alterações
        filtroCartuchoRepository.save(filtroCartucho);
        return new FiltroCartuchoDTO(filtroCartucho);
    }
}
