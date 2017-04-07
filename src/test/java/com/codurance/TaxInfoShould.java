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
            "11000.00, 11000.00, 0.00, 0.00",
            "5000.00, 5000.00, 0.00, 0.00"
    })
    public void
    calculate_zero_percent_tax_for_earnings_up_to_11000(BigDecimal annualGrossSalary,
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
            "12000.00, 11000.00, 1000.00, 200.00",
            "30000.00, 11000.00, 19000.00, 3800.00",
            "43000.00, 11000.00, 32000.00, 6400.00"
    })
    public void
    calculate_20_percent_tax_for_earnings_between_11000_and_43000(BigDecimal annualGrossSalary,
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
            "45000.00, 11000.00, 34000.00, 7200.00",
            "50000.00, 11000.00, 39000.00, 9200.00",
            "100000.00, 11000.00, 89000.00, 29200.00"
    })
    public void
    calculate_40_percent_tax_for_earnings_between_43000_and_150000(BigDecimal annualGrossSalary,
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
            "101000, 10500.00, 90500.00, 29800.00",
            "111000, 5500.00, 105500.00, 35800.00",
            "122000, 0.00, 122000.00, 42400.00",
            "150000, 0.00, 150000.00, 53600.00"
    })
    public void
    have_tax_free_allowance_decreased_by_1_for_every_2_pounds_for_salaries_above_100K(
                                                        BigDecimal annualGrossSalary,
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
            "160000.00, 0.00, 160000.00, 58100.00",
            "200000.00, 0.00, 200000.00, 76100.00"
    })
    public void
    calculate_45_percent_tax_for_earnings_above_150000(
                                                BigDecimal annualGrossSalary,
                                                BigDecimal taxFreeAllowance,
                                                BigDecimal taxableIncome,
                                                BigDecimal taxPayable) {
        TaxInfo taxInfo = new TaxInfo(annualGrossSalary);

        assertThat(taxInfo.taxFreeAllowance()).isEqualTo(taxFreeAllowance);
        assertThat(taxInfo.taxableIncome()).isEqualTo(taxableIncome);
        assertThat(taxInfo.taxPayable()).isEqualTo(taxPayable);
    }

}