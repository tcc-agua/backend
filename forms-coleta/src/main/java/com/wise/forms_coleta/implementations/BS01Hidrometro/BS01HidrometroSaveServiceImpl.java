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

    // Método de salvar nova coleta de BS01 Hidrometro
    @Override
    public BS01HidrometroDTO save(BS01HidrometroCreateDTO data) {

        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() ->new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade BS01 Hidrometro
        BS01Hidrometro bs01Hidrometro = new BS01Hidrometro(data);
        // Setando o ponto
        bs01Hidrometro.setPonto(ponto);
        // Associando a instância de BC01 criada a coleta
        coleta.getBs01HidrometroSet().add(bs01Hidrometro);

        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());

        // Salvando no banco a instância de BC01
        bs01HidrometroRepository.save(bs01Hidrometro);
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new BS01HidrometroDTO(bs01Hidrometro);
    }
}
