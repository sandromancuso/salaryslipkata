package com.codurance;

import java.math.BigDecimal;

public class Employee {

    private final int id;
    private final String name;
    private final BigDecimal anualGrossSalary;

    public Employee(int id, String name, BigDecimal anualGrossSalary) {
        this.id = id;
        this.name = name;
        this.anualGrossSalary = anualGrossSalary;
    }

    public int ID() {
        return id;
    }

    public String name() {
        return this.name;
    }

    public BigDecimal grossSalary() {
        return anualGrossSalary;
    }
}
