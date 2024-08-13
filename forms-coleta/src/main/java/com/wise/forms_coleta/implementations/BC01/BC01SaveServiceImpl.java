package com.wise.forms_coleta.implementations.BC01;

import com.wise.forms_coleta.dtos.bc01.BC01CreateDTO;
import com.wise.forms_coleta.dtos.bc01.BC01DTO;
import com.wise.forms_coleta.entities.BC01;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BC01Repository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.BC01.BC01SaveService;
import org.springframework.beans.factory.annotation.Autowired;

public class BC01SaveServiceImpl implements BC01SaveService {

    @Autowired
    private BC01Repository bc01Repo;

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public BC01DTO save(BC01CreateDTO data) {

        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        BC01 bc01 = new BC01(data);
        bc01.setPonto(ponto);

        bc01Repo.save(bc01);

        return  new BC01DTO(bc01);
    }
}
