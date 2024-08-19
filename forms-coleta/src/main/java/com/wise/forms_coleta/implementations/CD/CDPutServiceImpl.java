package com.wise.forms_coleta.implementations.CD;

import com.wise.forms_coleta.dtos.cd.CDDTO;
import com.wise.forms_coleta.dtos.cd.CDPutDTO;
import com.wise.forms_coleta.entities.CD;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.CDRepository;
import com.wise.forms_coleta.services.CD.CDPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CDPutServiceImpl implements CDPutService {

    @Autowired
    private CDRepository cdRepo;

    @Override
    public CDDTO put(Long id, CDPutDTO data) {
        CD cd = cdRepo.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado"));

        cd.setTipo_rede(data.tipo_rede());
        cd.setPressao(data.pressao());
        cd.setHidrometro(data.hidrometro());

        cdRepo.save(cd);
        return new CDDTO(cd);

    }
}
