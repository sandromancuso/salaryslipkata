package com.codurance;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class TaxInfoShould {
    
    @Test
    @Parameters({
            "11000, 11000, 0, 0",
            "5000, 5000, 0, 0"
    })
    public void
    have_tax_of_zero_for_annual_salaries_up_to_11000(BigDecimal annualGrossSalary,
                                                     BigDecimal taxFreeAllowance,
                                                     BigDecimal taxableIncome,
                                                     BigDecimal taxPayable) {
        TaxInfo taxInfo = new TaxInfo(annualGrossSalary);

        assertThat(taxInfo.taxFreeAllowance()).isEqualTo(taxFreeAllowance);
        assertThat(taxInfo.taxableIncome()).isEqualTo(taxableIncome);
        assertThat(taxInfo.taxPayable()).isEqualTo(taxPayable);
    }

    @Test
    @Parameters({
            "12000, 11000, 1000, 200.00",
            "30000, 11000, 19000, 3800.00",
            "43000, 11000, 32000, 6400.00"
    })
    public void
    have_20_percent_tax_for_annual_salaries_between_11000_and_43000(BigDecimal annualGrossSalary,
                                                                    BigDecimal taxFreeAllowance,
                                                                    BigDecimal taxableIncome,
                                                                    BigDecimal taxPayable) {
        TaxInfo taxInfo = new TaxInfo(annualGrossSalary);

        assertThat(taxInfo.taxFreeAllowance()).isEqualTo(taxFreeAllowance);
        assertThat(taxInfo.taxableIncome()).isEqualTo(taxableIncome);
        assertThat(taxInfo.taxPayable()).isEqualTo(taxPayable);
    }

    @Test
    @Parameters({
            "45000, 11000, 34000, 7200.00",
            "50000, 11000, 39000, 9200.00"
    })
    public void
    have_40_percent_tax_for_annual_salaries_above_43000(BigDecimal annualGrossSalary,
                                                        BigDecimal taxFreeAllowance,
                                                        BigDecimal taxableIncome,
                                                        BigDecimal taxPayable) {
        TaxInfo taxInfo = new TaxInfo(annualGrossSalary);

        assertThat(taxInfo.taxFreeAllowance()).isEqualTo(taxFreeAllowance);
        assertThat(taxInfo.taxableIncome()).isEqualTo(taxableIncome);
        assertThat(taxInfo.taxPayable()).isEqualTo(taxPayable);
    }

}