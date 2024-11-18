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

    // Método de salvar nova coleta de fase livre
    @Override
    public FaseLivreDTO save(FaseLivreCreateDTO data) {

        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade fase livre
        FaseLivre faseLivre = new FaseLivre(data);

        // Setando o ponto
        faseLivre.setPonto(ponto);

        // Associando a instância de fase livre criada a coleta
        coleta.getFaseLivreSet().add(faseLivre);

        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());

        // Salvando no banco a instância de fase livre
        faseLivreRepository.save(faseLivre);
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new FaseLivreDTO(faseLivre);
    }
}
