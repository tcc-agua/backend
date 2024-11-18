package com.wise.forms_coleta.implementations.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoDTO;
import com.wise.forms_coleta.dtos.ponto.PontoPutDTO;
import com.wise.forms_coleta.entities.Excel;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ExcelRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.ponto.PontoPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PontoPutServiceImpl implements PontoPutService {
    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ExcelRepository excelRepository;

    // Método de alterar a instância de ponto
    @Override
    public PontoDTO put(String name, PontoPutDTO data) {

        // Encontrando a instância de ponto pelo nome
        Ponto ponto = pontoRepository.findByNome(name)
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Encontrando a instância de excel pelo nome
        Excel excel = excelRepository.findByNome(data.excel())
                .orElseThrow(() -> new GenericsNotFoundException("Excel não encontrado!"));


        // Setando as alterações nos campos
        ponto.setNome(data.nome());
        ponto.setLocalizacao(data.localizacao());
        ponto.setStatus(data.statusEnum());
        ponto.setExcel(excel);

        // Salvando no banco as alterações
        pontoRepository.save(ponto);
        return new PontoDTO(ponto);
    }

}
