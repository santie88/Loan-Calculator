package com.seiras.lendico.test.exception;

public class LoanAmountNotValidException extends RuntimeException {
    public LoanAmountNotValidException(){super("The amount of the loan should be greater that 0");}
}
