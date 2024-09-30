package com.wise.forms_coleta.implementations.bomba_bc03;

import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03DTO;
import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03PutDTO;
import com.wise.forms_coleta.entities.BombaBc03;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BombaBc03Repository;
import com.wise.forms_coleta.services.bomba_bc03.BombaBc03PutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BombaBc03PutServiceImpl implements BombaBc03PutService {

    @Autowired
    private BombaBc03Repository bc03Repository;


    @Override
    public BombaBc03DTO put(Long id, BombaBc03PutDTO data) {
        BombaBc03 bombaBc03 = bc03Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        bombaBc03.setHidrometro(data.hidrometro());
        bombaBc03.setHorimetro(data.horimetro());
        bombaBc03.setPressao(data.pressao());

        bc03Repository.save(bombaBc03);

        return new BombaBc03DTO(bombaBc03);
    }
}
