package com.wise.forms_coleta.dtos.coleta;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record ColetaCreateDTO(@NotBlank String tecnico,
                              @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataColeta,
                              @DateTimeFormat(pattern = "HH:mm:ss")LocalTime horaInicio,
                              @DateTimeFormat(pattern = "HH:mm:ss")LocalTime horaFim
                              ) {
}
