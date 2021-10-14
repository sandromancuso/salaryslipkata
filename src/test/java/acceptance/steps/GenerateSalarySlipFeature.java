package acceptance.steps;

import com.codurance.Employee;
import com.codurance.SalarySlip;
import com.codurance.SalarySlipGenerator;
import io.cucumber.java8.En;

import java.math.BigDecimal;

import static com.codurance.EmployeeBuilder.anEmployee;
import static java.math.RoundingMode.HALF_UP;
import static org.assertj.core.api.Assertions.assertThat;

public class GenerateSalarySlipFeature implements En {

    private SalarySlip salarySlip;
    private Employee employee;

    private SalarySlipGenerator salarySlipGenerator;

    public GenerateSalarySlipFeature() {

        Given("^an employee with an annual gross salary of £ (\\d+)$", (BigDecimal annualGrossSalary) -> {
            employee = anEmployee().withAnnualGrossSalary(annualGrossSalary).build();
            salarySlipGenerator = new SalarySlipGenerator();
        });

        When("^we generate the salary slip$", () -> {
            salarySlip = salarySlipGenerator.generateFor(employee);
        });

        Then("^the salary slip should contain monthly gross salary of £ (\\d+\\.\\d+)$", (BigDecimal monthlyGrossSalary) -> {
            assertThat(salarySlip.monthlyGrossSalary()).isEqualTo(twoDecimalCases(monthlyGrossSalary));
        });

        Then("^national insurance contribution of £ (\\d+\\.\\d+)$", (BigDecimal nationalInsurance) -> {
            assertThat(salarySlip.nationalInsurance()).isEqualTo(twoDecimalCases(twoDecimalCases(nationalInsurance)));
        });

        Then("^tax-free allowance of £ (\\d+\\.\\d+)$", (BigDecimal taxFreeAllowance) -> {
            assertThat(salarySlip.taxFreeAllowance()).isEqualTo(twoDecimalCases(twoDecimalCases(taxFreeAllowance)));
        });

        Then("^taxable income of £ (\\d+\\.\\d+)$", (BigDecimal taxableIncome) -> {
            assertThat(salarySlip.taxableIncome()).isEqualTo(twoDecimalCases(twoDecimalCases(taxableIncome)));
        });

        Then("^tax payable of £ (\\d+\\.\\d+)$", (BigDecimal taxPayble) -> {
            assertThat(salarySlip.taxPayable()).isEqualTo(twoDecimalCases(twoDecimalCases(taxPayble)));
        });

    }

    private BigDecimal twoDecimalCases(BigDecimal source) {
        return source.setScale(2, HALF_UP);
    }
}
