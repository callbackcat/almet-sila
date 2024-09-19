package entities;

import enums.CompanyType;
import interfaces.Employee;

public class Company implements Employee {

    private long id;
    private String email;
    private String phone;
    private String address;
    private BankAccount bankAccount;
    private String companyName;
    private CompanyType companyType;

    public Company(long id, String email, String phone, String address, BankAccount bankAccount, String companyName, CompanyType type) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bankAccount = bankAccount;
        this.companyName = companyName;
        this.companyType = type;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
