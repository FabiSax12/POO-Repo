package Models;

public class Customer extends Person {
    private String accountNumber;

    public Customer(String firstName, String lastName, String accountNumber) {
        super(firstName, lastName);

        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
