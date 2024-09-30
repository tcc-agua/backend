package com.wise.forms_coleta.implementations.faseLivre;

import com.wise.forms_coleta.dtos.faseLivre.FaseLivreDTO;
import com.wise.forms_coleta.dtos.faseLivre.FaseLivrePutDTO;
import com.wise.forms_coleta.entities.FaseLivre;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.FaseLivreRepository;
import com.wise.forms_coleta.services.faseLivre.FaseLivrePutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaseLivrePutServiceImpl implements FaseLivrePutService {
    @Autowired
    private FaseLivreRepository faseLivreRepository;

    @Override
    public FaseLivreDTO put(Long id, FaseLivrePutDTO data) {
        FaseLivre faseLivre = faseLivreRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        faseLivre.setVolume(data.volume());
        faseLivre.setHouve_troca(data.houve_troca());

        faseLivreRepository.save(faseLivre);

        return new FaseLivreDTO(faseLivre);
    }
}
