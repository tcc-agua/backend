package com.wise.forms_coleta.implementations.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoDTO;
import com.wise.forms_coleta.dtos.ponto.PontoPutDTO;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.ponto.PontoPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PontoPutServiceImpl implements PontoPutService {
    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public PontoDTO put(String name, PontoPutDTO data) {
        Optional<Ponto> optionalPonto = pontoRepository.findByNome(name);
        if(optionalPonto.isPresent()){
            Ponto ponto = optionalPonto.get();
            ponto.setNome(data.nome());
            ponto.setLocalizacao(data.localizacao());
            ponto.setExcel(data.excel());
            ponto.setStatusEnum(data.statusEnum());
            pontoRepository.save(ponto);
            return new PontoDTO(ponto);
        }
        throw new GenericsNotFoundException("Ponto não encontrado!");
    }
}
