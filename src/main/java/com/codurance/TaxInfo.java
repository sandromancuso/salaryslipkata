package com.codurance;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

class TaxInfo {

    private static final BigDecimal NO_TAX = new BigDecimal(0);
    private static final BigDecimal MAX_TAX_FREE_ALLOWANCE = BigDecimal.valueOf(11000);
    private static final BigDecimal HIGHER_SALARY_LIMIT = BigDecimal.valueOf(43000);

    private static final BigDecimal NORMAL_TAX_RATE = BigDecimal.valueOf(0.20);
    private static final BigDecimal HIGHER_TAX_RATE = BigDecimal.valueOf(0.40);

    private BigDecimal annualGrossSalary;

    TaxInfo(BigDecimal annualGrossSalary) {
        this.annualGrossSalary = annualGrossSalary;
    }

    BigDecimal taxFreeAllowance() {
        return isGrossSalaryAboveMaxTaxFreeAllowance()
                    ? MAX_TAX_FREE_ALLOWANCE
                    : annualGrossSalary;
    }

    BigDecimal taxableIncome() {
        return (isGrossSalaryAboveMaxTaxFreeAllowance())
                    ? annualGrossSalary.subtract(MAX_TAX_FREE_ALLOWANCE)
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
            taxAtHigherRate = multiply(taxableAmount, HIGHER_TAX_RATE);
        }
        return taxAtHigherRate;
    }

    private BigDecimal taxPayableAtNormalRate() {
        BigDecimal taxAtNormalRate = NO_TAX;
         if (annualGrossSalary.doubleValue() > MAX_TAX_FREE_ALLOWANCE.doubleValue()) {
            BigDecimal taxableAmount = (annualGrossSalary.doubleValue() > HIGHER_SALARY_LIMIT.doubleValue())
                                            ? HIGHER_SALARY_LIMIT
                                            : annualGrossSalary;
            taxAtNormalRate = multiply(taxableAmount.subtract(MAX_TAX_FREE_ALLOWANCE), NORMAL_TAX_RATE);
        }
        return taxAtNormalRate;
    }

    private BigDecimal taxPayableOf(BigDecimal twentyPercent) {
        return annualGrossSalary.subtract(MAX_TAX_FREE_ALLOWANCE).multiply(twentyPercent);
    }

    private boolean isGrossSalaryAboveMaxTaxFreeAllowance() {
        return annualGrossSalary.compareTo(MAX_TAX_FREE_ALLOWANCE) == 1;
    }

    private BigDecimal multiply(BigDecimal amount, BigDecimal multiplicand) {
        return amount.multiply(multiplicand).setScale(2, ROUND_HALF_UP);
    }

}
