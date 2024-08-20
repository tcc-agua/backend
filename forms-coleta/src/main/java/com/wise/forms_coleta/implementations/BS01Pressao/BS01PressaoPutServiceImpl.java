package com.wise.forms_coleta.implementations.BS01Pressao;

import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoDTO;
import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoPutDTO;
import com.wise.forms_coleta.entities.BS01Pressao;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BS01PressaoRepository;
import com.wise.forms_coleta.services.BS01Pressao.BS01PressaoPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BS01PressaoPutServiceImpl implements BS01PressaoPutService {

    @Autowired
    BS01PressaoRepository bs01PressaoRepository;

    @Override
    public BS01PressaoDTO put(Long id, BS01PressaoPutDTO data) {
        BS01Pressao bs01Pressao = bs01PressaoRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        bs01Pressao.setPressao(data.pressao());

        bs01PressaoRepository.save(bs01Pressao);
        return new BS01PressaoDTO(bs01Pressao);
    }
}
