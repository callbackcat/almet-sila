package src.interfaces;

import src.entities.BankAccount;

public interface Employee {
    long getId();
    String getEmail();
    String getPhone();
    String getAddress();
    BankAccount getBankAccount();
}
