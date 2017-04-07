package com.codurance;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

class NationalInsurance {

    private static final BigDecimal MAX_SALARY_WITH_NO_CONTRIBUTION = twoDecimalCases(8060.00);
    private static final BigDecimal HIGHER_RATE_SALARY = twoDecimalCases(43000.00);
    private static final double NORMAL_CONTRIBUTION_RATE = 0.12;
    private static final double HIGHER_CONTRIBUTION_RATE = 0.02;

    private BigDecimal annualSalary;
    private BigDecimal contribution;

    NationalInsurance(BigDecimal annualSalary) {
        this.annualSalary = annualSalary;
        this.contribution = calculateContribution();
    }

    BigDecimal contribution() {
        return contribution;
    }

    private BigDecimal calculateContribution() {
        BigDecimal contributionAtNormalRate = contributionAtNormalRate();
        BigDecimal contributionAtHigherRate = contributionAtHigherRate();

        return contributionAtNormalRate.add(contributionAtHigherRate);
    }

    private BigDecimal contributionAtHigherRate() {
        BigDecimal taxableAmount = new BigDecimal(0);
        if (annualSalary.doubleValue() > HIGHER_RATE_SALARY.doubleValue()) {
            taxableAmount = annualSalary.subtract(HIGHER_RATE_SALARY);
        }
        return multiply(taxableAmount, HIGHER_CONTRIBUTION_RATE);
    }

    private BigDecimal contributionAtNormalRate() {
        BigDecimal taxableAmount = new BigDecimal(0);
        if (annualSalary.doubleValue() > MAX_SALARY_WITH_NO_CONTRIBUTION.doubleValue()) {
            BigDecimal taxableAtNormalRate = (annualSalary.doubleValue() > HIGHER_RATE_SALARY.doubleValue())
                                                    ? HIGHER_RATE_SALARY
                                                    : annualSalary;
            taxableAmount = taxableAtNormalRate.subtract(MAX_SALARY_WITH_NO_CONTRIBUTION);
        }
        return multiply(taxableAmount, NORMAL_CONTRIBUTION_RATE);
    }

    private BigDecimal multiply(BigDecimal amount, double multiplicand) {
        return amount.multiply(BigDecimal.valueOf(multiplicand)).setScale(2, ROUND_HALF_UP);
    }

    private static BigDecimal twoDecimalCases(double source) {
        return new BigDecimal(source).setScale(2, ROUND_HALF_UP);
    }

}
