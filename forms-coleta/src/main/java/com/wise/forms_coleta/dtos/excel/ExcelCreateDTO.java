package com.wise.forms_coleta.dtos.excel;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ExcelCreateDTO(
        @NotBlank(message = "O campo 'nome' não pode estar em branco!")
        String nome
){
}
