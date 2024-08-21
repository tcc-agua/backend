package com.wise.forms_coleta.implementations.bomba_bc03;

import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03CreateDTO;
import com.wise.forms_coleta.dtos.bomba_bc03.BombaBc03DTO;
import com.wise.forms_coleta.entities.BombaBc03;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BombaBc03Repository;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.bomba_bc03.BombaBc03SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class BombaBc03SaveServiceImpl implements BombaBc03SaveService {

    @Autowired
    private PontoRepository pontoRepository;
    @Autowired
    private ColetaRepository coletaRepository;
    @Autowired
    private BombaBc03Repository bombaBc03Repository;


    @Override
    public BombaBc03DTO save(BombaBc03CreateDTO data) {
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        Coleta coleta = new Coleta(data.nomeTecnico(), LocalDate.now(), LocalTime.now());

        BombaBc03 bombaBc03 = new BombaBc03(data);
        bombaBc03.setPonto(ponto);

        coleta.getBombaBc03Set().add(bombaBc03);

        coleta.setHora_fim(LocalTime.now());

        coletaRepository.save(coleta);
        bombaBc03Repository.save(bombaBc03);

        return new BombaBc03DTO(bombaBc03);
    }
}
