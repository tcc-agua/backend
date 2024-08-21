package com.wise.forms_coleta.implementations.BS01Hidrometro;

import com.wise.forms_coleta.dtos.BS01Hidrometro.BS01HidrometroCreateDTO;
import com.wise.forms_coleta.dtos.BS01Hidrometro.BS01HidrometroDTO;
import com.wise.forms_coleta.entities.BS01Hidrometro;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BS01HidrometroRepository;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.BS01Hidrometro.BS01HidrometroSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class BS01HidrometroSaveServiceImpl implements BS01HidrometroSaveService {
    @Autowired
    BS01HidrometroRepository bs01HidrometroRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public BS01HidrometroDTO save(BS01HidrometroCreateDTO data) {
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        Coleta coleta = new Coleta(data.nomeTecnico(), LocalDate.now(), LocalTime.now());

        BS01Hidrometro bs01Hidrometro = new BS01Hidrometro(data);
        bs01Hidrometro.setPonto(ponto);

        coleta.getBs01HidrometroSet().add(bs01Hidrometro);
        coleta.setHora_fim(LocalTime.now());

        bs01HidrometroRepository.save(bs01Hidrometro);
        coletaRepository.save(coleta);

        return new BS01HidrometroDTO(bs01Hidrometro);
    }
}
