package src.entities;

import src.interfaces.Employee;

public class Individual implements Employee {
    private long id;
    private String email;
    private String phone;
    private String address;
    private BankAccount bankAccount;
    private String name;
    private String surname;
    private boolean hasChildren;
    private int age;

    public Individual(long id, String email, String phone, String address, BankAccount bankAccount, String name, String surname, boolean hasChildren, int age) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bankAccount = bankAccount;
        this.name = name;
        this.surname = surname;
        this.hasChildren = hasChildren;
        this.age = age;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
