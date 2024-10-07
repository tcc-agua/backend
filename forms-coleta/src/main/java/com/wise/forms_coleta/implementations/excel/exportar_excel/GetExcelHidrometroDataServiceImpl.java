package com.wise.forms_coleta.implementations.excel.exportar_excel;

import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Excel;
import com.wise.forms_coleta.entities.Hidrometro;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.ExcelRepository;
import com.wise.forms_coleta.services.exportar_excel.GetExcelHidrometroDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetExcelHidrometroDataServiceImpl implements GetExcelHidrometroDataService {

    @Autowired
    private ExcelRepository excelRepository;
    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public List<List<Object>> readExcelHidrometroFile(String sheetName, LocalDate startDate, LocalDate endDate) {
        List<Coleta> coletas = coletaRepository.findAllByDataColetaBetween(startDate, endDate);
        Excel excel = excelRepository.findByNome(sheetName)
                .orElseThrow(() -> new GenericsNotFoundException("Excel não encontrado!"));

        List<List<Object>> dadosComHeaders = new ArrayList<>();

        for(Coleta coleta : coletas){
            for(Hidrometro hidrometro : coleta.getHidrometroSet()){
                if (hidrometro.getPonto().getExcel() == excel){
                    List<Object> dados = new ArrayList<>();
                    dados.add(hidrometro.getPonto().getNome()); // Primeiro elemento é o header
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dadosSemId.put("volume", hidrometro.getVolume());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }
        }
        return dadosComHeaders;
    }
}
