package com.wise.forms_coleta.implementations.hidrometro;

import com.wise.forms_coleta.dtos.hidrometro.HidrometroCreateDTO;
import com.wise.forms_coleta.dtos.hidrometro.HidrometroDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Hidrometro;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.HidrometroRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.hidrometro.HidrometroSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class HidrometroSaveServiceImpl implements HidrometroSaveService {

    @Autowired
    private HidrometroRepository hidrometroRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public HidrometroDTO save(HidrometroCreateDTO data) {
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() -> new GenericsNotFoundException("Coleta não encontrada!"));

        Hidrometro hidrometro = new Hidrometro(data);
        hidrometro.setPonto(ponto);
        coleta.getHidrometroSet().add(hidrometro);


        hidrometroRepository.save(hidrometro);
        coleta.setHora_fim(LocalTime.now());
        coletaRepository.save(coleta);

        return new HidrometroDTO(hidrometro);


    }
}
