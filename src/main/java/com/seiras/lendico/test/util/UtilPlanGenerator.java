package com.seiras.lendico.test.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UtilPlanGenerator {

    public static Double round(Double value) {
        return round(value, 2);
    }

    public static Double round(Double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double calculateAnnuity(double nominalRate, double loanAmount, double duration){
        double ratePerPeriod = ((nominalRate/12)/100);

        return ((loanAmount * ratePerPeriod)
                / (1 - Math.pow(1 + ratePerPeriod, duration * -1)));
    }

    public static double calculateInterest(double nominalRate,
                                           double loanAmount,
                                           int daysOfPeriod,
                                           int daysOfNominalRatePeriod ){

        return ((nominalRate/100) * daysOfPeriod * loanAmount)/daysOfNominalRatePeriod;
    }

}
