package com.wise.forms_coleta.exceptions;

// Exceção para se determinada entidade já existir
public class GenericsAlreadyExistsException extends RuntimeException {
    public GenericsAlreadyExistsException(String message){
        super(message);
    }
}
