package com.codurance;

public class SalarySlipGenerator {

    public SalarySlip generateFor(Employee employee) {
        TaxInfo taxInfo = new TaxInfo(employee.grossSalary());
        NationalInsurance nationalInsurance = new NationalInsurance(employee.grossSalary());
        return new SalarySlip(employee, nationalInsurance, taxInfo);
    }
}
