package com.wise.forms_coleta.implementations.BC06;

import com.wise.forms_coleta.dtos.BC06.BC06DTO;
import com.wise.forms_coleta.dtos.BC06.BC06PutDTO;
import com.wise.forms_coleta.entities.BC06;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BC06Repository;
import com.wise.forms_coleta.services.BC06.BC06PutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BC06PutServiceImpl implements BC06PutService {

    // Método de alterar a instância de BC06
    @Autowired
    private BC06Repository bc06Repository;

    @Override
    public BC06DTO put(Long id, BC06PutDTO data) {
        // Encontrando a instância de BC06 pelo id
        BC06 bc06 = bc06Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));
        // Setando as alterações nos campos
        bc06.setHorimetro(data.horimetro());
        bc06.setPressao(data.pressao());
        // Salvando no banco as alterações
        bc06Repository.save(bc06);
        return new BC06DTO(bc06);
    }
}
