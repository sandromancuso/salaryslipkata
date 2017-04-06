package com.codurance;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static com.codurance.EmployeeBuilder.anEmployee;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class SalarySlipShould {


    public static final Employee AN_EMPLOYEE = anEmployee().build();

    @Test
    @Parameters({
            "6000, 500.00",
            "11000, 916.67",
            "12000, 916.67",
            "30000, 916.67"
    })
    public void
    should_inform_monthly_tax_free_allowance(BigDecimal annualGrossSalary, BigDecimal monthlyTaxFreeAllowance) {
        SalarySlip salarySlip = new SalarySlip(AN_EMPLOYEE, new TaxInfo(annualGrossSalary));

        assertThat(salarySlip.taxFreeAllowance()).isEqualTo(monthlyTaxFreeAllowance);
    }

    @Test
    @Parameters({
            "6000, 0.00",
            "11000, 0.00",
            "12000, 83.33",
            "30000, 1583.33"
    })
    public void
    should_inform_monthly_taxable_income(BigDecimal annualGrossSalary, BigDecimal monthlyTaxableIncome) {
        SalarySlip salarySlip = new SalarySlip(AN_EMPLOYEE, new TaxInfo(annualGrossSalary));

        assertThat(salarySlip.taxableIncome()).isEqualTo(monthlyTaxableIncome);
    }

    @Test
    @Parameters({
            "6000, 0.00",
            "11000, 0.00",
            "12000, 16.67",
            "30000, 316.67"
    })
    public void
    should_inform_monthly_tax_payable(BigDecimal annualGrossSalary, BigDecimal monthlyTaxableIncome) {
        SalarySlip salarySlip = new SalarySlip(AN_EMPLOYEE, new TaxInfo(annualGrossSalary));

        assertThat(salarySlip.taxPayable()).isEqualTo(monthlyTaxableIncome);
    }

}