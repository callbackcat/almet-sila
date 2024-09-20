package src.entities;

public class BankAccount {
    private String iban;
    private String bic;
    private String accountHolder;

    public BankAccount(String iban, String bic, String accountHolder) {
        this.iban = iban;
        this.bic = bic;
        this.accountHolder = accountHolder;
    }

    public String getIban() {
        return iban;
    }

    public String getBic() {
        return bic;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
}
