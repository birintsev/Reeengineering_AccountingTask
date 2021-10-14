import lombok.Getter;
import lombok.Setter;

public class Account {

    @Getter
    @Setter
    private String iban;

    @Getter
    private AccountType type;

    @Getter
    private int daysOverdrawn;

    @Getter
    @Setter
    private double money;

    @Getter
    @Setter
    private String currency;

    @Getter
    @Setter
    private Customer customer;

    public Account(AccountType type, int daysOverdrawn) {
        super();
        this.type = type;
        this.daysOverdrawn = daysOverdrawn;
    }

    public double bankcharge() {
        double result = 4.5;

        result += overdraftCharge();

        return result;
    }

    private double overdraftCharge() {
        if (type == AccountType.PREMIUM) {
            double result = 10;
            if (getDaysOverdrawn() > 7)
                result += (getDaysOverdrawn() - 7) * 1.0;
            return result;
        } else
            return getDaysOverdrawn() * 1.75;
    }

    public double overdraftFee() {
        if (type == AccountType.PREMIUM) {
            return 0.10;
        } else {
            return 0.20;
        }
    }

    public String printCustomer() {
        return customer.getName() + " " + customer.getEmail();
    }

    String getIbanDaysOverdrawn() {
        return "Account: IBAN: " + getIban() + ", Days Overdrawn: " + getDaysOverdrawn();
    }
}
