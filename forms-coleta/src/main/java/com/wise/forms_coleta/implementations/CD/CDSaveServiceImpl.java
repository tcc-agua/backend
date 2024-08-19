package com.wise.forms_coleta.implementations.CD;

import com.wise.forms_coleta.dtos.cd.CDCreateDTO;
import com.wise.forms_coleta.dtos.cd.CDDTO;
import com.wise.forms_coleta.entities.CD;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.CDRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.CD.CDSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CDSaveServiceImpl implements CDSaveService {
    @Autowired
    private CDRepository cdRepo;

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public CDDTO save(CDCreateDTO data) {
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        CD cd = new CD(data);
        cd.setPonto(ponto);

        cdRepo.save(cd);

        return  new CDDTO(cd);
    }
}
