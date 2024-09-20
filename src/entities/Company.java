package src.entities;

import src.enums.CompanyType;
import src.interfaces.Employee;

public class Company extends Employee {
    private String companyName;
    private CompanyType companyType;

    public Company(long id, String email, String phone, String address, BankAccount bankAccount, String companyName, CompanyType companyType) {
        super(id, email, phone, address, bankAccount);
        this.companyName = companyName;
        this.companyType = companyType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }
}