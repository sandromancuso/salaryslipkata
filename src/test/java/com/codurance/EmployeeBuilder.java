package com.codurance;

import java.math.BigDecimal;

public class EmployeeBuilder {

    private int id = 12345;
    private String name = "John J Doe";
    private BigDecimal annualGrossSalary = BigDecimal.valueOf(5000);

    public static EmployeeBuilder anEmployee() {
        return new EmployeeBuilder();
    }


    public EmployeeBuilder withID(int id) {
        this.id = id;
        return this;
    }

    public EmployeeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder withAnnualGrossSalary(BigDecimal annualGrossSalary) {
        this.annualGrossSalary = annualGrossSalary;
        return this;
    }

    public EmployeeBuilder withAnnualGrossSalary(double annualGrossSalary) {
        this.annualGrossSalary = BigDecimal.valueOf(annualGrossSalary);
        return this;
    }

    public Employee build() {
        return new Employee(id, name, annualGrossSalary);
    }
}
