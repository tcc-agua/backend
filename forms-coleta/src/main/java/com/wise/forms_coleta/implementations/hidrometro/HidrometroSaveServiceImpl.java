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

    // Método de salvar nova coleta de hidrometro
    @Override
    public HidrometroDTO save(HidrometroCreateDTO data) {
        // Pegando a instância de ponto de acordo com o nome passado
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Pegando a instância de coleta de acordo com o id passado
        Coleta coleta = coletaRepository.findById(data.idColeta())
                .orElseThrow(() -> new GenericsNotFoundException("Coleta não encontrada!"));

        // Nova entidade hidrometro
        Hidrometro hidrometro = new Hidrometro(data);
        // Setando o ponto
        hidrometro.setPonto(ponto);
        // Associando a instância de hidrometro criada a coleta
        coleta.getHidrometroSet().add(hidrometro);

        // Salvando no banco a instância de hidrometro
        hidrometroRepository.save(hidrometro);
        // Setando a hora de fim da coleta
        coleta.setHora_fim(LocalTime.now());
        // Salvando no banco a instância de coleta
        coletaRepository.save(coleta);

        return new HidrometroDTO(hidrometro);


    }
}
