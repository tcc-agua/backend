package com.wise.forms_coleta.implementations.BH02;

import com.wise.forms_coleta.dtos.bh02.BH02CreateDTO;
import com.wise.forms_coleta.dtos.bh02.BH02DTO;
import com.wise.forms_coleta.entities.BH02;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BH02Repository;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.BH02.BH02SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class BH02SaveServiceImpl implements BH02SaveService {

    @Autowired
    private BH02Repository bh02Repository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public BH02DTO save(BH02CreateDTO data) {
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        Coleta coleta = new Coleta(data.nomeTecnico(), LocalDate.now(), LocalTime.now());

        BH02 bh02 = new BH02(data);
        bh02.setPonto(ponto);
        coleta.getBh02Set().add(bh02);

        coleta.setHora_fim(LocalTime.now());

        bh02Repository.save(bh02);
        coletaRepository.save(coleta);

        return new BH02DTO(bh02);

    }

}
