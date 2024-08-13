package com.wise.forms_coleta.implementations.BC06;

import com.wise.forms_coleta.dtos.BC06.BC06CreateDTO;
import com.wise.forms_coleta.dtos.BC06.BC06DTO;
import com.wise.forms_coleta.entities.BC06;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BC06Repository;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.BC06.BC06SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BC06SaveServiceImpl implements BC06SaveService {
    @Autowired
    private BC06Repository bc06Repository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;


    @Override
    public BC06DTO save(BC06CreateDTO data) {
        if(coletaRepository.findById(data.idColeta()).isPresent() && pontoRepository.findByNome(data.nomePonto()).isPresent()){
            BC06 bc06 = new BC06(data);
            bc06.setFk_coleta(coletaRepository.findById(data.idColeta()).orElseThrow(() -> new GenericsNotFoundException("Coleta não encontrada!")));
            bc06.setFk_ponto(pontoRepository.findByNome(data.nomePonto()).get());
            bc06Repository.save(bc06);
            return new BC06DTO(bc06);
        }
        throw new GenericsNotFoundException("Ponto ou Coleta não encontrados!");
    }
}
