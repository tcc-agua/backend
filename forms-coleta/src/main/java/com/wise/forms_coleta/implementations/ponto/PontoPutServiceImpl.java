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

    @Override
    public PontoDTO put(String name, PontoPutDTO data) {
        Ponto ponto = pontoRepository.findByNome(name)
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        Excel excel = excelRepository.findByNome(data.excel())
                .orElseThrow(() -> new GenericsNotFoundException("Excel não encontrado!"));

        ponto.setNome(data.nome());
        ponto.setLocalizacao(data.localizacao());
        ponto.setStatus(data.statusEnum());
        ponto.setExcel(excel);

        pontoRepository.save(ponto);
        return new PontoDTO(ponto);
    }

}
