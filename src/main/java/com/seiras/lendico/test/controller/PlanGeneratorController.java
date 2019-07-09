package com.seiras.lendico.test.controller;

import com.seiras.lendico.test.model.PlanGenerator;
import com.seiras.lendico.test.model.RepaymentPlan;
import com.seiras.lendico.test.service.PlanGeneratorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlanGeneratorController {

    private PlanGeneratorService planGeneratorService;

    PlanGeneratorController(PlanGeneratorService planGeneratorService){
        this.planGeneratorService = planGeneratorService;
    }

    @PostMapping("/generate-plan")
    public List<RepaymentPlan> getCalculatedRepaymentPlan(@RequestBody PlanGenerator planGenerator){
        return planGeneratorService.calculateRepaymentPlan(planGenerator);
    }
}
