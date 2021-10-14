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

    public void withdraw(double sum, String currency) {
        Account account = getAccount();
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        account.setMoney(
            account.getMoney() < 0
            ? calculateAccountMoneyAfterWithdrawCredit(sum)
            : calculateAccountMoneyAfterWithdraw(sum)
        );
    };

    public String printCustomerAccount() {
        return "Account: IBAN: " + account.getIban() + ", Money: "
                + account.getMoney() + ", Account type: " + account.getType();
    }

    protected double calculateAccountMoneyAfterWithdraw(double sum) {
        return getAccount().getMoney() - sum;
    }

    protected double calculateAccountMoneyAfterWithdrawCredit(double sum) {
        Account account = getAccount();
        return (account.getMoney() - sum) - sum * account.overdraftFee();
    }
}
