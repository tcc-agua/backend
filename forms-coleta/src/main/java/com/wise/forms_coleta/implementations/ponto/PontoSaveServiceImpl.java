package com.wise.forms_coleta.implementations.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoCreateDTO;
import com.wise.forms_coleta.dtos.ponto.PontoDTO;
import com.wise.forms_coleta.entities.Excel;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ExcelRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.ponto.PontoSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PontoSaveServiceImpl implements PontoSaveService {

    @Autowired
    private PontoRepository pontoRepository;
    @Autowired
    private ExcelRepository excelRepository;

    // métodos

    @Override
    public PontoDTO save(PontoCreateDTO data) {

        Excel excel = excelRepository.findByNome(data.nome_excel())
                .orElseThrow(() -> new GenericsNotFoundException("Planilha não encontrada!"));

        Ponto ponto = new Ponto(data);

        ponto.setExcel(excel);

        return new PontoDTO(pontoRepository.save(ponto));
    }

}
