package com.codurance;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SalarySlipGeneratorShould {

    private static final int ID = 123445;
    private static final String NAME = "John J Doe";
    private static final BigDecimal GROSS_SALARY = new BigDecimal(5000);
    private static final BigDecimal MONTHLY_GROSS_SALARY = BigDecimal.valueOf(416.67);

    private SalarySlipGenerator salarySlipGenerator;

    @Before
    public void initialise() {
        salarySlipGenerator = new SalarySlipGenerator();
    }

    @Test public void
    generate_basic_salary_slip() {
        Employee employee = new Employee(ID, NAME, GROSS_SALARY);

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.employeeID()).isEqualTo(employee.ID());
        assertThat(salarySlip.employeeName()).isEqualTo(employee.name());
        assertThat(salarySlip.monthlyGrossSalary()).isEqualTo(MONTHLY_GROSS_SALARY);
    }

}
