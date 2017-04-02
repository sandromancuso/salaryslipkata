Salary slip kata
================
## Problem description: Salary slip generator for UK companies.

  A typical salary slip contains employee details like employee id, employee name and their monthly salary details like their gross salary, national insurance contributions, tax-free allowance, taxable income and tax payable.

  Salary slips are generated each month for every employee.


### Acceptance criteria:
- Salary slip generator should receive an employee with its Employee Id, Employee Name and Annual Gross Salary
- Salary slip should contain the Employee ID, Employee Name, Gross Salary, National Insurance contributions, Tax-free allowance, Taxable income and Tax payable for the month
- The entry point should be the following interface, which you can not change:

Java: 
```java

      public class SalarySlipGenerator {
        public SalarySlip generateFor(Employee employee);
      }

```

C#:
```csharp
    public class SalarySlipGenerator
    {
        public SalarySlip GenerateFor(Employee employee);
    }
```

You can, however, add **private** methods and fields to the `SalarySlipGenerator` class, change the constructor, and add extra classes.

## Iterations

Each iteration adds more rules to the calculation. Some iterations also introduce new fields to the salary slip.

In a given iteration, all the salary slips contain the same number fields for each employee (if a tax or contribution does not apply for a given employee, just put £0.00).

This means that for each iteration you will need to add fields to the `SalarySlip` class. In the first iteration, `SalarySlip` only contains the Employee ID, Employee Name and Monthly Gross Salary.

#### Iteration 1: for an annual salary of £5,000.00
This is the most basic case.

  <p>*Given* I have an employee John J Doe with an annual gross salary of £5,000.00</p>
  <p>*When* I generate a monthly salary slip for the employee</p>
  <p>*Then* the monthly salary slip should contain the below:</p>

           Employee ID: 12345
           Employee Name: John J Doe
           Gross Salary: £416.67

Calculation rules:
 * Monthly Gross Salary: The monthly gross salary is the employee's annual gross salary divided by 12

#### Iteration 2: for an annual gross salary of £9,060.00

Here we introduce the National Insurance contribution

  <p>The monthly salary slip should contain the below:</p>

           Employee ID: 12345
           Employee Name: John J Doe
           Gross Salary: £755.00
           National Insurance contributions: £10.00

Calculation rules:
 * National Insurance contributions: Any amount of money earned above a gross annual salary of £8,060.00 is subject to a National Insurance contribution of 12%

#### Iteration 3: for an annual gross salary of £12,000.00

This employee also needs to pay taxes

  <p>The monthly salary slip should contain the below:</p>

           Employee ID: 12345
           Employee Name: John J Doe
           Gross Salary: £1,000.00
           National Insurance contributions: £39.40
           Tax-free allowance: £916.67
           Taxable income: £83.33
           Tax Payable: £16.67

Calculation rules:
 * Taxable income: Any amount of money earned above a gross annual salary of £11,000.00 is taxed at 20%

#### Iteration 4: for an annual gross salary of £45,000.00

This employee pays a higher band of National Insurance and Income Tax.

  <p>The monthly salary slip should contain the below:</p>

           Employee ID: 12345
           Employee Name: John J Doe
           Gross Salary: £3,750.00
           National Insurance contributions: £352.73
           Tax-free allowance: £916.67
           Taxable income: £2,833.33
           Tax Payable: £600.00

Calculation rules:
 * Taxable income (higher rate): Any amount of money earned above a gross annual salary of £43,000.00 is taxed at 40%
 * National Insurance (higher contributions): Any amount of money earned above a gross annual salary of £43,000.00 is only subject to a 2% NI contribution

#### Iteration 5: for annual gross salaries of £101,000.00; £111,000.00; £122,000.00 and £150,000.00

For high earners, the tax-free allowance decreases.

  <p>The monthly salary slips should contain the below (respectively):</p>

           Employee ID: 12345
           Employee Name: John J Doe
           Gross Salary: £8,416.67
           National Insurance contributions: £446.07
           Tax-free allowance: £875.00
           Taxable income: £7,541.67
           Tax Payable: £2,483.33


           Employee ID: 12345
           Employee Name: John J Doe
           Gross Salary: £9,250.00
           National Insurance contributions: £462.73
           Tax-free allowance: £458.33
           Taxable income: £8,791.67
           Tax Payable: £2,983.33


           Employee ID: 12345
           Employee Name: John J Doe
           Gross Salary: £10,166.67
           National Insurance contributions: £481.07
           Tax-free allowance: £0.00
           Taxable income: £10,166.67
           Tax Payable: £3,533.33


           Employee ID: 12345
           Employee Name: John J Doe
           Gross Salary: £12,500.00
           National Insurance contributions: £527.73
           Tax-free allowance: £0.00
           Taxable income: £12,500.00
           Tax Payable: £4,466.67

Calculation rules:
 * Tax-free allowance: When the Annual Gross Salary exceeds £100,000.00, the tax-free allowance starts decreasing. It decreases by £1 for every £2 earned over £100,000.00. And this excess is taxed at the Higher rate tax.

#### Iteration 9: for an annual gross salary of £160,000.00

The employee goes into the additional rate band.

  <p>The monthly salary slip should contain the below:</p>

          Employee ID: 12345
          Employee Name: John J Doe
          Gross Salary: £13,333.33
          National Insurance contributions: £544.40
          Tax-free allowance: £0.00
          Taxable income: £13,333.33
          Tax Payable: £4,841.67


Calculation rules:
 * Income tax (additional rate band) : Any amount of money earned above a gross annual salary of £150,000.00 is taxed at 45%


## Details on calculation rules

The [DETAILS.md](DETAILS.md) file contains a condensed version of the calculation rules with more examples and links to additional resources.

You do not need to read it to complete the Kata, but it is provided to you as an extra help.