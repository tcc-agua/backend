package com.wise.forms_coleta.exceptions;

// Exceção para entidade não encontrada
public class GenericsNotFoundException extends RuntimeException{
    public GenericsNotFoundException(String message){
        super(message);
    }
}
