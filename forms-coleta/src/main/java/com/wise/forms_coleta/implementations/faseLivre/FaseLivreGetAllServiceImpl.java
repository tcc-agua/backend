package com.wise.forms_coleta.implementations.faseLivre;

import com.wise.forms_coleta.dtos.faseLivre.FaseLivreDTO;
import com.wise.forms_coleta.repositories.FaseLivreRepository;
import com.wise.forms_coleta.services.faseLivre.FaseLivreGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaseLivreGetAllServiceImpl implements FaseLivreGetAllService {
    @Autowired
    private FaseLivreRepository faseLivreRepository;

    @Override
    public List<FaseLivreDTO> getAll() {
        return faseLivreRepository.findAll().stream().map(FaseLivreDTO::new).toList();
    }
}
