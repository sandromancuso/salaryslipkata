package com.codurance;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static com.codurance.EmployeeBuilder.anEmployee;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class SalarySlipGeneratorShould {

    private static final int ID = 123445;
    private static final String NAME = "John J Doe";

    private SalarySlipGenerator salarySlipGenerator;

    @Before
    public void initialise() {
        salarySlipGenerator = new SalarySlipGenerator();
    }

    @Test public void
    generate_salary_slip_with_basic_employee_information() {
        Employee employee = anEmployee().withID(ID).withName(NAME).build();

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.employeeID()).isEqualTo(employee.ID());
        assertThat(salarySlip.employeeName()).isEqualTo(employee.name());
    }

    @Test public void
    generate_slip_with_month_gross_salary() {
        Employee employee_1 = anEmployee().withAnnualGrossSalary(5000).build();
        Employee employee_2 = anEmployee().withAnnualGrossSalary(9060).build();

        SalarySlip salarySlip_1 = salarySlipGenerator.generateFor(employee_1);
        SalarySlip salarySlip_2 = salarySlipGenerator.generateFor(employee_2);

        assertThat(salarySlip_1.monthlyGrossSalary()).isEqualTo(BigDecimal.valueOf(416.67));
        assertThat(salarySlip_2.monthlyGrossSalary().intValue()).isEqualTo(755);
    }
    
    @Test
    @Parameters({
            "8060, 0",
            "5000, 0"
    })
    public void
    generate_slip_with_national_insurance_set_to_zero_when_salary_is_up_to_8060_00(double annualSalary, double nationalInsurance) {
        Employee employee = anEmployee().withAnnualGrossSalary(annualSalary).build();

        SalarySlip salarySlip_1 = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip_1.nationalInsurance().doubleValue()).isEqualTo(nationalInsurance);
    }

    @Test
    @Parameters({
            "9060, 10",
            "10060, 20"
    })
    public void
    generate_slip_with_12_percent_national_insurance_for_earnings_above_8060_00(double annualSalary, double nationalInsurance) {
        Employee employee = anEmployee().withAnnualGrossSalary(annualSalary).build();

        SalarySlip salarySlip_1 = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip_1.nationalInsurance().doubleValue()).isEqualTo(nationalInsurance);
    }

    @Test public void
    generate_slip_with_tax_info() {
        Employee employee = anEmployee().build();

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.taxInfo()).isNotNull();
    }
}
