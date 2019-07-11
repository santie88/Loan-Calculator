package com.seiras.lendico.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class NominalRateNotValidException extends RuntimeException {
    public NominalRateNotValidException(){super("The nominal rate of the loan should be greater that 0");}
}
