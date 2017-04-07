package com.codurance;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

class TaxInfo {

    private static final BigDecimal NO_TAX = twoDecimalCases(0);
    private static final BigDecimal MAX_TAX_FREE_ALLOWANCE = twoDecimalCases(11000);
    private static final BigDecimal NO_ALLOWANCE = twoDecimalCases(0.00);
    private static final BigDecimal HIGHER_SALARY_LIMIT = twoDecimalCases(43000);
    private static final BigDecimal ONE_HUNDRED_THOUSAND = twoDecimalCases(100000);

    private static final BigDecimal NORMAL_TAX_RATE = twoDecimalCases(0.20);
    private static final BigDecimal HIGHER_TAX_RATE = twoDecimalCases(0.40);

    private BigDecimal annualGrossSalary;
    private BigDecimal taxFreeAllowance;

    TaxInfo(BigDecimal annualGrossSalary) {
        this.annualGrossSalary = annualGrossSalary;
        this.taxFreeAllowance = calculateTaxFreeAllowance(annualGrossSalary);
        System.out.println("tax free allowance: " + taxFreeAllowance);
    }

    BigDecimal taxFreeAllowance() {
        return taxFreeAllowance;
    }

    BigDecimal taxableIncome() {
        return (isGrossSalaryAboveMaxTaxFreeAllowance())
                    ? annualGrossSalary.subtract(taxFreeAllowance())
                    : NO_TAX;
    }

    BigDecimal taxPayable() {
        BigDecimal taxPayableAtNormalRate = taxPayableAtNormalRate();
        BigDecimal taxPayableAtHigherRate = taxPayableAtHigherRate();

        return taxPayableAtNormalRate.add(taxPayableAtHigherRate);
    }

    private BigDecimal taxPayableAtHigherRate() {
        BigDecimal taxAtHigherRate = NO_TAX;
        if (annualGrossSalary.doubleValue() > HIGHER_SALARY_LIMIT.doubleValue()) {
            BigDecimal taxableAmount = annualGrossSalary.subtract(HIGHER_SALARY_LIMIT);

            BigDecimal taxFreeDiff = MAX_TAX_FREE_ALLOWANCE.subtract(taxFreeAllowance);
            taxableAmount = taxableAmount.add(taxFreeDiff);

            taxAtHigherRate = multiply(taxableAmount, HIGHER_TAX_RATE);
        }
        return taxAtHigherRate;
    }

    private BigDecimal taxPayableAtNormalRate() {
        BigDecimal taxAtNormalRate = NO_TAX;
         if (annualGrossSalary.doubleValue() > taxFreeAllowance.doubleValue()) {
            BigDecimal taxableAmount = (annualGrossSalary.doubleValue() > HIGHER_SALARY_LIMIT.doubleValue())
                                            ? HIGHER_SALARY_LIMIT
                                            : annualGrossSalary;

             BigDecimal taxFreeDiff = MAX_TAX_FREE_ALLOWANCE.subtract(taxFreeAllowance);
             taxableAmount = taxableAmount.subtract(taxFreeDiff);

             taxAtNormalRate = multiply(taxableAmount.subtract(taxFreeAllowance), NORMAL_TAX_RATE);
        }
        return taxAtNormalRate;
    }

    private BigDecimal calculateTaxFreeAllowance(BigDecimal annualGrossSalary) {
        if (annualGrossSalary.doubleValue() > ONE_HUNDRED_THOUSAND.doubleValue()) {
            BigDecimal excess = annualGrossSalary.subtract(ONE_HUNDRED_THOUSAND);
            BigDecimal decreasedAllowance = excess.divide(twoDecimalCases(2));
            return (decreasedAllowance.doubleValue() > MAX_TAX_FREE_ALLOWANCE.doubleValue())
                    ? NO_ALLOWANCE
                    : MAX_TAX_FREE_ALLOWANCE.subtract(decreasedAllowance);
        } else if (isGrossSalaryAboveMaxTaxFreeAllowance()) {
            return MAX_TAX_FREE_ALLOWANCE;
        } else {
            return annualGrossSalary;
        }
    }

    private boolean isGrossSalaryAboveMaxTaxFreeAllowance() {
        return annualGrossSalary.compareTo(MAX_TAX_FREE_ALLOWANCE) == 1;
    }

    private BigDecimal multiply(BigDecimal amount, BigDecimal multiplicand) {
        return amount.multiply(multiplicand).setScale(2, ROUND_HALF_UP);
    }

    private static BigDecimal twoDecimalCases(double source) {
        return new BigDecimal(source).setScale(2, ROUND_HALF_UP);
    }

}
