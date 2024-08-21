package com.wise.forms_coleta.infra.dtos;

import org.springframework.validation.FieldError;

public record ExceptionDTO(
        String field,
        String message
){

    public ExceptionDTO(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
