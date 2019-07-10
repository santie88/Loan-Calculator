package com.seiras.lendico.test.exception;

public class NominalRateNotValidException extends RuntimeException {
    public NominalRateNotValidException(){super("The nominal rate of the loan should be greater that 0");}
}
