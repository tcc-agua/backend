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

    // Método de alterar a instância de fase livre
    @Override
    public FaseLivreDTO put(Long id, FaseLivrePutDTO data) {
        // Encontrando a instância de fase livre pelo id
        FaseLivre faseLivre = faseLivreRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        // Setando as alterações nos campos
        faseLivre.setVolume(data.volume());
        faseLivre.setHouve_troca(data.houve_troca());

        // Salvando no banco as alterações
        faseLivreRepository.save(faseLivre);

        return new FaseLivreDTO(faseLivre);
    }
}
