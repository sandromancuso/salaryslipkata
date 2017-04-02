package com.codurance;

public class SalarySlipGenerator {

    public SalarySlip generateFor(Employee employee) {
        return new SalarySlip(employee);
    }
}
