package com.wise.forms_coleta.implementations.BS01Pressao;

import com.wise.forms_coleta.dtos.BS01Pressao.BS01PressaoDTO;
import com.wise.forms_coleta.repositories.BS01PressaoRepository;
import com.wise.forms_coleta.services.BS01Pressao.BS01PressaoGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BS01PressaoGetAllServiceImpl implements BS01PressaoGetAllService {

    @Autowired
    BS01PressaoRepository bs01PressaoRepository;

    @Override
    public List<BS01PressaoDTO> getAll() {
        return bs01PressaoRepository.findAll().stream().map(BS01PressaoDTO::new).toList();
    }

}
