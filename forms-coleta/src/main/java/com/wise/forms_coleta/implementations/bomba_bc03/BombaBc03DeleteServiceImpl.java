package com.wise.forms_coleta.implementations.bomba_bc03;

import com.wise.forms_coleta.entities.BombaBc03;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.BombaBc03Repository;
import com.wise.forms_coleta.services.bomba_bc03.BombaBc03DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BombaBc03DeleteServiceImpl implements BombaBc03DeleteService {
    @Autowired
    private BombaBc03Repository bc03Repository;

    @Override
    public String delete(Long id) {
        BombaBc03 bombaBc03 = bc03Repository.findById(id)
                .orElseThrow(() -> new GenericsNotFoundException("Formulário não encontrado!"));

        bc03Repository.delete(bombaBc03);

        return "Formulário deletado com sucesso!";
    }
}
