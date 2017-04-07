package com.codurance;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

class NationalInsurance {

    private static final BigDecimal MAX_SALARY_WITH_NO_CONTRIBUTION = twoDecimalCases(8060.00);
    private static final BigDecimal HIGHER_RATE_SALARY = twoDecimalCases(43000.00);
    private static final double NORMAL_CONTRIBUTION_RATE = 0.12;
    private static final double HIGHER_CONTRIBUTION_RATE = 0.02;

    private BigDecimal annualSalary;

    NationalInsurance(BigDecimal annualSalary) {
        this.annualSalary = annualSalary;
    }

    BigDecimal contribution() {
        BigDecimal taxableAmountAtNormalRate = taxableAmountAtNormalRate();
        BigDecimal taxableAmountAtHigherRate = taxableAmountAtHigherRate();

        return multiply(taxableAmountAtNormalRate, NORMAL_CONTRIBUTION_RATE)
                .add(multiply(taxableAmountAtHigherRate, HIGHER_CONTRIBUTION_RATE));
    }

    private BigDecimal taxableAmountAtHigherRate() {
        BigDecimal taxableAmount = new BigDecimal(0);
        if (annualSalary.doubleValue() > HIGHER_RATE_SALARY.doubleValue()) {
            taxableAmount = annualSalary.subtract(HIGHER_RATE_SALARY);
        }
        return taxableAmount;
    }

    private BigDecimal taxableAmountAtNormalRate() {
        BigDecimal taxableAmount = new BigDecimal(0);
        if (annualSalary.doubleValue() > MAX_SALARY_WITH_NO_CONTRIBUTION.doubleValue()) {
            BigDecimal taxableAtNormalRate = (annualSalary.doubleValue() > HIGHER_RATE_SALARY.doubleValue())
                                                    ? HIGHER_RATE_SALARY
                                                    : annualSalary;
            taxableAmount = taxableAtNormalRate.subtract(MAX_SALARY_WITH_NO_CONTRIBUTION);
        }
        return taxableAmount;
    }

    private BigDecimal multiply(BigDecimal amount, double multiplicand) {
        return amount.multiply(BigDecimal.valueOf(multiplicand)).setScale(2, ROUND_HALF_UP);
    }

    private static BigDecimal twoDecimalCases(double source) {
        return new BigDecimal(source).setScale(2, ROUND_HALF_UP);
    }

}
