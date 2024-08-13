package com.wise.forms_coleta.dtos.coleta;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record ColetaCreateDTO(
        @NotBlank(message = "O campo 'técnico' não pode estar em branco!")
        String tecnico,

        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate dataColeta,

        @DateTimeFormat(pattern = "HH:mm:ss")
        LocalTime horaInicio,

        @DateTimeFormat(pattern = "HH:mm:ss")
        LocalTime horaFim

        // Pensar em como Receber o ID da entidade coletada
){
}
