package com.wise.forms_coleta.implementations.faseLivre;

import com.wise.forms_coleta.dtos.faseLivre.FaseLivreCreateDTO;
import com.wise.forms_coleta.dtos.faseLivre.FaseLivreDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.FaseLivre;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.FaseLivreRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.faseLivre.FaseLivreSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class FaseLivreSaveServiceImpl implements FaseLivreSaveService {

    @Autowired
    private FaseLivreRepository faseLivreRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;


    @Override
    public FaseLivreDTO save(FaseLivreCreateDTO data) {

        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        FaseLivre faseLivre = new FaseLivre(data);

        faseLivre.setPonto(ponto);

        coleta.getFaseLivreSet().add(faseLivre);

        coleta.setHora_fim(LocalTime.now());

        faseLivreRepository.save(faseLivre);
        coletaRepository.save(coleta);

        return new FaseLivreDTO(faseLivre);
    }
}
