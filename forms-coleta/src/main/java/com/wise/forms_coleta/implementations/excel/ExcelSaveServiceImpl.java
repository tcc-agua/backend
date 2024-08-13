package com.wise.forms_coleta.implementations.excel;

import com.wise.forms_coleta.dtos.excel.ExcelCreateDTO;
import com.wise.forms_coleta.dtos.excel.ExcelDTO;
import com.wise.forms_coleta.entities.Excel;
import com.wise.forms_coleta.repositories.ExcelRepository;
import com.wise.forms_coleta.services.excel.ExcelSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelSaveServiceImpl implements ExcelSaveService {
    @Autowired
    private ExcelRepository excelRepository;

    @Override
    public ExcelDTO save(ExcelCreateDTO data) {
        return new ExcelDTO(excelRepository.save(new Excel(data)));
    }
}
