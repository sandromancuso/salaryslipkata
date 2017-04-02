package com.codurance;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.codurance.EmployeeBuilder.anEmployee;
import static org.assertj.core.api.Assertions.assertThat;

public class SalarySlipGeneratorShould {

    private static final int ID = 123445;
    private static final String NAME = "John J Doe";
    private static final BigDecimal GROSS_SALARY = new BigDecimal(5000);

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

}
