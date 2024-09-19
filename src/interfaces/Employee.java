package interfaces;

import entities.BankAccount;

public interface Employee {
    long getId();
    String getEmail();
    String getPhone();
    String getAddress();
    BankAccount getBankAccount();
}
