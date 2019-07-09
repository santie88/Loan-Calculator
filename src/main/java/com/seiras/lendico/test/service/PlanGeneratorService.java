package com.seiras.lendico.test.service;

import com.seiras.lendico.test.model.PlanGenerator;
import com.seiras.lendico.test.model.RepaymentPlan;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.seiras.lendico.test.util.UtilPlanGenerator.*;

@Service
public class PlanGeneratorService {

    private List<RepaymentPlan> repaymentPlanList = new ArrayList<>();

    public List<RepaymentPlan> calculateRepaymentPlan(PlanGenerator planGenerator){

        Double annuity = round(calculateAnnuity(planGenerator.getNominalRate(),
                                                planGenerator.getLoanAmount(),
                                                planGenerator.getDuration()),2);

        RepaymentPlan repaymentPlan = new RepaymentPlan();

        repaymentPlan.setBorrowerPaymentAmount(annuity.toString());

        LocalDateTime date = planGenerator.getStartDate();

        repaymentPlan.setDate(date);

        Double initialOutstandingPrincipal = planGenerator.getLoanAmount();

        repaymentPlan.setInitialOutstandingPrincipal(initialOutstandingPrincipal.toString());

        Double interest = calculateInterest(planGenerator.getNominalRate(),
                                            initialOutstandingPrincipal,
                                30,360);

        repaymentPlan.setInterest(round(interest, 2).toString());

        Double principal = annuity - interest;

        repaymentPlan.setPrincipal(round(principal, 2).toString());

        Double remainingOutstandingPrincipal = initialOutstandingPrincipal - principal;

        repaymentPlan.setRemainingOutstandingPrincipal(round(remainingOutstandingPrincipal, 2).toString());

        repaymentPlanList.add(repaymentPlan);

        return repaymentPlanList;
    }


}
