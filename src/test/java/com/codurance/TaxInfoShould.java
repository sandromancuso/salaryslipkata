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
            "12000, 11000, 1000, 200.0",
            "30000, 11000, 19000, 3800.0"
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

}