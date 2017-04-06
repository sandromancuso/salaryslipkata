package com.codurance;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class SalarySlip {

    private static final int TWO_DECIMAL_CASES = 2;
    private static final BigDecimal TWELVE_MONTHS = new BigDecimal(12);
    private static final double NATIONAL_INSURANCE_CONTRIBUTION = 0.12;
    private static final BigDecimal NO_CONTRIBUTION = twoDecimalCases(new BigDecimal(0.00));

    private Employee employee;
    private TaxInfo taxInfo;

    public SalarySlip(Employee employee, TaxInfo taxInfo) {
        this.employee = employee;
        this.taxInfo = taxInfo;
    }

    public int employeeID() {
        return employee.ID();
    }

    public String employeeName() {
        return employee.name();
    }

    public BigDecimal monthlyGrossSalary() {
        return divideBy12(employee.grossSalary());
    }

    public BigDecimal nationalInsurance() {
        BigDecimal salaryExcess = employee.grossSalary().subtract(BigDecimal.valueOf(8060));
        return salaryExcess.doubleValue() > 0
                    ? divideBy12(multiply(salaryExcess, NATIONAL_INSURANCE_CONTRIBUTION))
                    : NO_CONTRIBUTION;
    }

    public BigDecimal taxFreeAllowance() {
        return divideBy12(taxInfo.taxFreeAllowance());
    }

    public BigDecimal taxableIncome() {
        return divideBy12(taxInfo.taxableIncome());
    }

    public BigDecimal taxPayable() {
        return divideBy12(taxInfo.taxPayable());
    }

    private BigDecimal divideBy12(BigDecimal amount) {
        return amount.divide(TWELVE_MONTHS, TWO_DECIMAL_CASES, ROUND_HALF_UP);
    }

    private BigDecimal multiply(BigDecimal amount, double multiplicand) {
        return amount.multiply(BigDecimal.valueOf(multiplicand)).setScale(2, ROUND_HALF_UP);
    }

    private static BigDecimal twoDecimalCases(BigDecimal source) {
        return source.setScale(2, ROUND_HALF_UP);
    }
}
