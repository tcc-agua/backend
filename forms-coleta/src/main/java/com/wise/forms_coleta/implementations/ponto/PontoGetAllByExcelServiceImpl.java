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

    // Método que retorna todos os pontos filtrando por excel
    @Override
    public List<PontoDTO> getAllPointsByExcel(String excel) {
        // Pegando a instância de excel de acordo com o nome
        Excel excel1 = excelRepository.findByNome(excel)
                .orElseThrow(() -> new GenericsNotFoundException("Excel não encontrado!"));

        // Lista de todos os pontos filtrados de acordo com o excel recuperado anteriormente
        List<Ponto> pontos = pontoRepository.findAllByExcel(excel1);

        // Se a lista estiver vazia, uma exceção é retornada
        if (pontos.isEmpty()) {
            throw new GenericsNotFoundException("Nenhum ponto encontrado para este Excel!");
        }

        // Retornando os pontos
        return pontos.stream()
                .map(PontoDTO::new)
                .collect(Collectors.toList());
    }
}
