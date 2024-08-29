package com.wise.forms_coleta.implementations.ponto;

import com.wise.forms_coleta.dtos.ponto.PontoDTO;
import com.wise.forms_coleta.entities.Excel;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ExcelRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.ponto.PontoGetAllByExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontoGetAllByExcelServiceImpl implements PontoGetAllByExcelService {

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ExcelRepository excelRepository;

    @Override
    public List<PontoDTO> getAllPointsByExcel(String excel) {
        Excel excel1 = excelRepository.findByNome(excel)
                .orElseThrow(() -> new GenericsNotFoundException("Excel não encontrado!"));

        List<Ponto> pontos = pontoRepository.findAllByExcel(excel1);

        if (pontos.isEmpty()) {
            throw new GenericsNotFoundException("Nenhum ponto encontrado para este Excel!");
        }

        return pontos.stream()
                .map(PontoDTO::new) // Supondo que você tenha um construtor em PontoDTO que receba um Ponto
                .collect(Collectors.toList());
    }
}
