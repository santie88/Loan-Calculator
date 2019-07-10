package com.seiras.lendico.test.exception;

public class DurationNotValidException extends RuntimeException {
    public DurationNotValidException(){super("The duration of the loan should be greater that 0");}
}
