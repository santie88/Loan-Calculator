package com.seiras.lendico.test.validator;

import com.seiras.lendico.test.exception.DurationNotValidException;
import com.seiras.lendico.test.exception.LoanAmountNotValidException;
import com.seiras.lendico.test.exception.NominalRateNotValidException;
import com.seiras.lendico.test.model.PlanGenerator;
import org.springframework.stereotype.Service;

@Service
public class PlanGeneratorValidator {

    public void validate(PlanGenerator planGenerator){
        if(planGenerator.getLoanAmount() <= 0) {
            throw new LoanAmountNotValidException();
        }

        if(planGenerator.getNominalRate() <= 0) {
            throw new NominalRateNotValidException();
        }

        if(planGenerator.getDuration() <= 0) {
            throw new DurationNotValidException();
        }
    }

}
