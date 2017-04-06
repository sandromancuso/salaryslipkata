package com.codurance;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

class NationalInsurance {

    private static final BigDecimal MAX_SALARY_WITH_NO_CONTRIBUTION = BigDecimal.valueOf(8060);
    private static final BigDecimal NO_CONTRIBUTION = twoDecimalCases(new BigDecimal(0.00));
    private static final double NATIONAL_INSURANCE_CONTRIBUTION = 0.12;

    private BigDecimal annualSalary;

    NationalInsurance(BigDecimal annualSalary) {
        this.annualSalary = annualSalary;
    }

    BigDecimal contribution() {
        BigDecimal salaryExcess = annualSalary.subtract(MAX_SALARY_WITH_NO_CONTRIBUTION);
        return salaryExcess.doubleValue() > 0
                ? multiply(salaryExcess, NATIONAL_INSURANCE_CONTRIBUTION)
                : NO_CONTRIBUTION;

    }

    private BigDecimal multiply(BigDecimal amount, double multiplicand) {
        return amount.multiply(BigDecimal.valueOf(multiplicand)).setScale(2, ROUND_HALF_UP);
    }
    private static BigDecimal twoDecimalCases(BigDecimal source) {
        return source.setScale(2, ROUND_HALF_UP);
    }

}
