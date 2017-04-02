package com.codurance;

import java.math.BigDecimal;

import static java.math.RoundingMode.CEILING;

public class SalarySlip {

    private static final int TWO_DECIMAL_CASES = 2;
    private static final BigDecimal TWELVE_MONTHS = new BigDecimal(12);
    private Employee employee;

    public SalarySlip(Employee employee) {
        this.employee = employee;
    }

    int employeeID() {
        return employee.ID();
    }

    String employeeName() {
        return employee.name();
    }

    BigDecimal monthlyGrossSalary() {
        return employee.grossSalary().divide(TWELVE_MONTHS, TWO_DECIMAL_CASES, CEILING);
    }
}
