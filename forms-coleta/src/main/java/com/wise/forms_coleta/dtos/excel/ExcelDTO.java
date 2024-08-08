package com.wise.forms_coleta.dtos.excel;

import com.wise.forms_coleta.entities.Excel;

import java.time.LocalDate;

public record ExcelDTO(
        Long id,
        String nome,
        LocalDate data_coleta
){

    public ExcelDTO(Excel excel){
        this(excel.getId(), excel.getNomeExcel(), excel.getData_coleta());
    }
}
