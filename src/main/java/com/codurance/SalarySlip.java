package com.codurance;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class SalarySlip {

    private static final int TWO_DECIMAL_CASES = 2;
    private static final BigDecimal TWELVE_MONTHS = new BigDecimal(12);

    final private Employee employee;
    final private NationalInsurance nationalInsurance;
    final private TaxInfo taxInfo;

    public SalarySlip(Employee employee,
                      NationalInsurance nationalInsurance,
                      TaxInfo taxInfo) {
        this.employee = employee;
        this.nationalInsurance = nationalInsurance;
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
        return divideBy12(nationalInsurance.contribution());
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
        return amount.divide(TWELVE_MONTHS, TWO_DECIMAL_CASES, HALF_UP);
    }

}
