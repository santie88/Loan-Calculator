package com.seiras.lendico.test;

import com.seiras.lendico.test.exception.DurationNotValidException;
import com.seiras.lendico.test.exception.LoanAmountNotValidException;
import com.seiras.lendico.test.exception.NominalRateNotValidException;
import com.seiras.lendico.test.model.PlanGenerator;
import com.seiras.lendico.test.model.RepaymentPlan;
import com.seiras.lendico.test.service.PlanGeneratorService;
import com.seiras.lendico.test.validator.PlanGeneratorValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.seiras.lendico.test.util.UtilPlanGenerator.round;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

	PlanGenerator planGenerator = new PlanGenerator();

	PlanGeneratorValidator planGeneratorValidator = new PlanGeneratorValidator();
	PlanGeneratorService planGeneratorService = new PlanGeneratorService(planGeneratorValidator);

	List<RepaymentPlan> repaymentPlanList = new ArrayList<>();

	@Before
	public void setup(){
		planGenerator.setLoanAmount(5000);
		planGenerator.setNominalRate(5);
		planGenerator.setDuration(24);
		planGenerator.setStartDate(LocalDateTime.now());

		repaymentPlanList = planGeneratorService.calculateRepaymentPlan(planGenerator);
	}

	@Test
	public void contextLoads() {
	}

	@Test(expected = LoanAmountNotValidException.class)
	public void loanAmountInvalid_shouldFail(){
		planGenerator.setLoanAmount(0);

		planGeneratorService.calculateRepaymentPlan(planGenerator);
	}

	@Test(expected = NominalRateNotValidException.class)
	public void nominalRateInvalid_shouldFail(){
		planGenerator.setNominalRate(0);

		planGeneratorService.calculateRepaymentPlan(planGenerator);
	}

	@Test(expected = DurationNotValidException.class)
	public void durationInvalid_shouldFail(){
		planGenerator.setDuration(0);

		planGeneratorService.calculateRepaymentPlan(planGenerator);
	}

	@Test
	public void shouldReturnDurationPayments(){
		assertEquals(repaymentPlanList.size(), planGenerator.getDuration().intValue());
	}

	@Test
	public void sumOfPrincipalShouldEqualToLoanAmount(){
		double sumOfPrincipal = repaymentPlanList.stream()
												.mapToDouble(RepaymentPlan::getPrincipal)
										   		.sum();

		assertEquals(sumOfPrincipal, planGenerator.getLoanAmount(), 2);
	}

	@Test
	public void allAnnuityEqualPrincipalPlusInterest(){
		boolean result = repaymentPlanList.stream()
										  .allMatch(repaymentPlan -> repaymentPlan.getBorrowerPaymentAmount() ==
											  round(repaymentPlan.getPrincipal() + repaymentPlan.getInterest()));

		assertTrue(result);
	}

}
