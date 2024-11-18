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

    // Método de salvar nova coleta de ponto
    @Override
    public PontoDTO save(PontoCreateDTO data) {
        // Pegando a instância de excel de acordo com o nome passado
        Excel excel = excelRepository.findByNome(data.nome_excel())
                .orElseThrow(() -> new GenericsNotFoundException("Planilha não encontrada!"));

        // Nova entidade ponto
        Ponto ponto = new Ponto(data);
        // Setando o excel
        ponto.setExcel(excel);

        // Retornando novo PontoDTO, passando como parâmetro o retorno do método de salvar o ponto no banco de dados
        return new PontoDTO(pontoRepository.save(ponto));
    }

}
