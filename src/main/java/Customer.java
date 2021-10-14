import lombok.Getter;
import lombok.Setter;

public class Customer {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String email;

    private String surname;

    @Getter
    @Setter
    private CustomerType customerType;
    private Account account;
    private double companyOverdraftDiscount = 1;

    public Customer(String name, String surname, String email, CustomerType customerType, Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.customerType = customerType;
        this.account = account;
    }

    // use only to create companies
    public Customer(String name, String email, Account account, double companyOverdraftDiscount) {
        this.name = name;
        this.email = email;
        this.customerType = CustomerType.COMPANY;
        this.account = account;
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    public void withdraw(double sum, String currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        if (account.getType() == AccountType.PREMIUM) {
            switch (customerType) {
                case COMPANY:
                    // we are in overdraft
                    if (account.getMoney() < 0) {
                        // 50 percent discount for overdraft for premium account
                        account.setMoney((account.getMoney() - sum) - sum * account.overdraftFee() * companyOverdraftDiscount / 2);
                    } else {
                        account.setMoney(account.getMoney() - sum);
                    }
                    break;
                case PERSON:
                    // we are in overdraft
                    if (account.getMoney() < 0) {
                        account.setMoney((account.getMoney() - sum) - sum * account.overdraftFee());
                    } else {
                        account.setMoney(account.getMoney() - sum);
                    }
                    break;
            }
        } else {
            switch (customerType) {
                case COMPANY:
                    // we are in overdraft
                    if (account.getMoney() < 0) {
                        // no discount for overdraft for not premium account
                        account.setMoney((account.getMoney() - sum) - sum * account.overdraftFee() * companyOverdraftDiscount);
                    } else {
                        account.setMoney(account.getMoney() - sum);
                    }
                    break;
                case PERSON:
                    // we are in overdraft
                    if (account.getMoney() < 0) {
                        account.setMoney((account.getMoney() - sum) - sum * account.overdraftFee());
                    } else {
                        account.setMoney(account.getMoney() - sum);
                    }
                    break;
            }
        }
    }

    public String printCustomerDaysOverdrawn() {
        String fullName = getFullName();

        String accountDescription = "Account: IBAN: " + account.getIban() + ", Days Overdrawn: " + account.getDaysOverdrawn();
        return fullName + accountDescription;
    }

    public String printCustomerMoney() {
        String fullName = getFullName();
        String accountDescription = "";
        accountDescription += "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney();
        return fullName + accountDescription;
    }

    public String printCustomerAccount() {
        return "Account: IBAN: " + account.getIban() + ", Money: "
                + account.getMoney() + ", Account type: " + account.getType();
    }

    private String getFullName() {
        return name + " " + surname + " ";
    }
}
