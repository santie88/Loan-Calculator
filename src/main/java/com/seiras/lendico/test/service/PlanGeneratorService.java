package com.seiras.lendico.test.service;

import com.seiras.lendico.test.model.PlanGenerator;
import com.seiras.lendico.test.model.RepaymentPlan;
import com.seiras.lendico.test.validator.PlanGeneratorValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.seiras.lendico.test.util.UtilPlanGenerator.*;

@Service
public class PlanGeneratorService {

    private final PlanGeneratorValidator planGeneratorValidator;

    private List<RepaymentPlan> repaymentPlanList = new ArrayList<>();

    public PlanGeneratorService(PlanGeneratorValidator planGeneratorValidator) {
        this.planGeneratorValidator = planGeneratorValidator;
    }

    public List<RepaymentPlan> calculateRepaymentPlan(PlanGenerator planGenerator){

        planGeneratorValidator.validate(planGenerator);

        Double annuity = round(calculateAnnuity(planGenerator.getNominalRate(),
                                                planGenerator.getLoanAmount(),
                                                planGenerator.getDuration()));

        Double initialOutstandingPrincipal = planGenerator.getLoanAmount();

        for(int i = 0; i < planGenerator.getDuration(); i++){

            RepaymentPlan repaymentPlan = new RepaymentPlan();

            LocalDateTime date = planGenerator.getStartDate().plusMonths(i);

            repaymentPlan.setDate(date);

            repaymentPlan.setInitialOutstandingPrincipal(round(initialOutstandingPrincipal));

            Double interest = calculateInterest(planGenerator.getNominalRate(),
                                                initialOutstandingPrincipal,
                                                30,360);

            repaymentPlan.setInterest(round(interest));

            if(annuity > initialOutstandingPrincipal){ //check for last payment
                annuity = initialOutstandingPrincipal + interest;
            }

            repaymentPlan.setBorrowerPaymentAmount(round(annuity));

            Double principal = annuity - interest;

            repaymentPlan.setPrincipal(round(principal));

            Double remainingOutstandingPrincipal = initialOutstandingPrincipal - principal;

            repaymentPlan.setRemainingOutstandingPrincipal(round(remainingOutstandingPrincipal));

            repaymentPlanList.add(repaymentPlan);

            initialOutstandingPrincipal = remainingOutstandingPrincipal;
        }

        return repaymentPlanList;
    }


}
