package com.codurance;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class SalarySlip {

    private static final int TWO_DECIMAL_CASES = 2;
    private static final BigDecimal TWELVE_MONTHS = new BigDecimal(12);
    public static final double NATIONAL_INSURANCE_CONTRIBUTION = 0.12;
    public static final BigDecimal NO_CONTRIBUTION = new BigDecimal(0);

    private Employee employee;
    private TaxInfo taxInfo;

    public SalarySlip(Employee employee, TaxInfo taxInfo) {
        this.employee = employee;
        this.taxInfo = taxInfo;
    }

    int employeeID() {
        return employee.ID();
    }

    String employeeName() {
        return employee.name();
    }

    BigDecimal monthlyGrossSalary() {
        return divideBy12(employee.grossSalary());
    }

    BigDecimal nationalInsurance() {
        BigDecimal salaryExcess = employee.grossSalary().subtract(BigDecimal.valueOf(8060));
        return salaryExcess.doubleValue() > 0
                    ? divideBy12(multiply(salaryExcess, NATIONAL_INSURANCE_CONTRIBUTION))
                    : NO_CONTRIBUTION;
    }

    BigDecimal taxFreeAllowance() {
        return divideBy12(taxInfo.taxFreeAllowance());
    }

    BigDecimal taxableIncome() {
        return divideBy12(taxInfo.taxableIncome());
    }

    BigDecimal taxPayable() {
        return divideBy12(taxInfo.taxPayable());
    }

    private BigDecimal divideBy12(BigDecimal amount) {
        return amount.divide(TWELVE_MONTHS, TWO_DECIMAL_CASES, ROUND_HALF_UP);
    }

    private BigDecimal multiply(BigDecimal amount, double multiplicand) {
        return amount.multiply(BigDecimal.valueOf(multiplicand)).setScale(2, ROUND_HALF_UP);
    }
}
