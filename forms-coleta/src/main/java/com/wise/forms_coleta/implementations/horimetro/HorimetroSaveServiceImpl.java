package com.wise.forms_coleta.implementations.horimetro;

import com.wise.forms_coleta.dtos.horimetro.HorimetroCreateDTO;
import com.wise.forms_coleta.dtos.horimetro.HorimetroDTO;
import com.wise.forms_coleta.entities.Horimetro;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.HorimetroRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.horimetro.HorimetroSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorimetroSaveServiceImpl implements HorimetroSaveService {

    @Autowired
    private HorimetroRepository horimetroRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public HorimetroDTO save(HorimetroCreateDTO data) {
        Ponto ponto = pontoRepository.findByNome(data.nomePonto())
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));
        Horimetro horimetro = new Horimetro(data);
        horimetro.setPonto(ponto);
        horimetroRepository.save(horimetro);
        return new HorimetroDTO(horimetro);
    }
}
