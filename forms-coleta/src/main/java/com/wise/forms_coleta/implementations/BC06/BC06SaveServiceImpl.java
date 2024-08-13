package com.wise.forms_coleta.implementations.BC06;

import com.wise.forms_coleta.dtos.BC06.BC06CreateDTO;
import com.wise.forms_coleta.dtos.BC06.BC06DTO;
import com.wise.forms_coleta.entities.BC06;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BC06Repository;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.BC06.BC06SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BC06SaveServiceImpl implements BC06SaveService {
    @Autowired
    private BC06Repository bc06Repository;

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public BC06DTO save(BC06CreateDTO data) {
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));
        BC06 bc06 = new BC06(data);
        bc06.setPonto(ponto);
        bc06Repository.save(bc06);
        return new BC06DTO(bc06);
    }
}
