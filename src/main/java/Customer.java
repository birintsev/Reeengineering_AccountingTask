import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class Customer {

    @Getter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter(AccessLevel.PROTECTED)
    private Account account;

    public Customer(String name, String email, Account account) {
        this.name = name;
        this.email = email;
        this.account = account;
    }

    public String getCustomerAccountString() {
        return "Account: IBAN: " + account.getIban() + ", Money: "
                + account.getMoney() + ", Account type: " + account.getAccountTypeName();
    }

    public String getCustomerString() {
        return getName() + " " + getEmail();
    }
}
