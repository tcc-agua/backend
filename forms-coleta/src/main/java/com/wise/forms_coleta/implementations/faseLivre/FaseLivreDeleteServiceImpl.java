package com.wise.forms_coleta.implementations.faseLivre;

import com.wise.forms_coleta.entities.FaseLivre;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.FaseLivreRepository;
import com.wise.forms_coleta.services.faseLivre.FaseLivreDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaseLivreDeleteServiceImpl implements FaseLivreDeleteService {
    @Autowired
    private FaseLivreRepository faseLivreRepository;

    // Método para deletar uma instância de fase livre
    @Override
    public String delete(Long id) {
        // Pegando a instância de fase livre pelo id
        FaseLivre faseLivre = faseLivreRepository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        // Deletando no banco a instância
        faseLivreRepository.delete(faseLivre);
        return "Formulário deletado com sucesso!";
    }
}

