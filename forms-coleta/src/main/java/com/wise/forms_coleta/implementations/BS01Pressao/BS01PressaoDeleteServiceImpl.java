package com.wise.forms_coleta.implementations.BS01Pressao;

import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoDTO;
import com.wise.forms_coleta.entities.BS01Pressao;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BS01PressaoRepository;
import com.wise.forms_coleta.services.BS01Pressao.BS01PressaoDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BS01PressaoDeleteServiceImpl implements BS01PressaoDeleteService {

    @Autowired
    BS01PressaoRepository bs01PressaoRepository;

    @Override
    public String delete(Long id) {
        BS01Pressao bs01Pressao = bs01PressaoRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        bs01PressaoRepository.delete(bs01Pressao);
        return "Formulário deletado com sucesso!";
    }
}
