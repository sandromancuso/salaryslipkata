package com.codurance;

public class SalarySlipGenerator {

    public SalarySlip generateFor(Employee employee) {
        TaxInfo taxInfo = new TaxInfo(employee.grossSalary());
        return new SalarySlip(employee, taxInfo);
    }
}
