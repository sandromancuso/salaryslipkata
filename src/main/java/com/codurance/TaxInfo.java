package com.codurance;

import java.math.BigDecimal;

class TaxInfo {

    private static final BigDecimal NO_TAX = new BigDecimal(0);
    private static final BigDecimal MAX_TAX_FREE_ALLOWANCE = BigDecimal.valueOf(11000);
    public static final BigDecimal TWENTY_PERCENT = BigDecimal.valueOf(0.20);
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
        return (isGrossSalaryAboveMaxTaxFreeAllowance())
                    ? taxPayableOf(TWENTY_PERCENT)
                    : NO_TAX;
    }

    private BigDecimal taxPayableOf(BigDecimal twentyPercent) {
        return annualGrossSalary.subtract(MAX_TAX_FREE_ALLOWANCE).multiply(twentyPercent);
    }

    private boolean isGrossSalaryAboveMaxTaxFreeAllowance() {
        return annualGrossSalary.compareTo(MAX_TAX_FREE_ALLOWANCE) == 1;
    }
}
