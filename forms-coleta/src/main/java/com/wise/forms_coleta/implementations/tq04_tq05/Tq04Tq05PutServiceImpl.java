package com.wise.forms_coleta.implementations.tq04_tq05;

import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05DTO;
import com.wise.forms_coleta.dtos.tq04_tq05.Tq04Tq05PutDTO;
import com.wise.forms_coleta.entities.Tq04Tq05;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.Tq04Tq05Repository;
import com.wise.forms_coleta.services.tq04_tq05.Tq04Tq05PutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Tq04Tq05PutServiceImpl implements Tq04Tq05PutService {
    @Autowired
    private Tq04Tq05Repository tq04Tq05Repository;

    @Override
    public Tq04Tq05DTO put(Long id, Tq04Tq05PutDTO data) {
        Tq04Tq05 tq04Tq05 = tq04Tq05Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));
        tq04Tq05.setHouve_preparo_solucao(data.houve_preparo_solucao());
        tq04Tq05.setQtd_bombonas(data.qtd_bombonas());
        tq04Tq05.setKg_bombonas(data.kg_bombonas());
        tq04Tq05.setHorimetro(data.horimetro());
        tq04Tq05.setHidrometro(data.hidrometro());

        tq04Tq05Repository.save(tq04Tq05);
        return new Tq04Tq05DTO(tq04Tq05);
    }
}
