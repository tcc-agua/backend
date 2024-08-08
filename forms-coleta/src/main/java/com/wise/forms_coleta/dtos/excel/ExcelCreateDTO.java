package com.wise.forms_coleta.dtos.excel;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ExcelCreateDTO(@NotBlank String nome, @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate data_coleta) {
}
