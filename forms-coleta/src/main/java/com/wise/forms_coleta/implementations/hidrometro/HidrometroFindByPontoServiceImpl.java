package com.wise.forms_coleta.implementations.hidrometro;

import com.wise.forms_coleta.dtos.hidrometro.HidrometroDTO;
import com.wise.forms_coleta.entities.Hidrometro;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.HidrometroRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.hidrometro.HidrometroFindByPontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HidrometroFindByPontoServiceImpl implements HidrometroFindByPontoService {

    @Autowired
    private HidrometroRepository hidrometroRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public List<HidrometroDTO> FindByPonto(String ponto) {
        Ponto ponto1 = pontoRepository.findByNome(ponto)
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        List<Hidrometro> hidrometros = hidrometroRepository.findAllByPonto(ponto1);

        if (hidrometros.isEmpty()) {
            throw new GenericsNotFoundException("Nenhuma coleta de hidrômetro encontrada para esse ponto.");
        }

        return hidrometros.stream()
                .map(HidrometroDTO::new)
                .collect(Collectors.toList());

    }
}
