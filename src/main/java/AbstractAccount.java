import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractAccount implements Account {

    private static double BASE_BANKCHARGE = 4.5;

    private static int DEFAULT_OVERDRAFT_FEE_DISCOUNT_COEFFICIENT = 1; // 1 means 100%

    private String iban;

    private double money;

    private int daysOverdrawn;

    private String currency;

    private Customer customer;

    private double overdraftFeeDiscount;

    private double overdraftFeeDiscountCoefficient;

    public AbstractAccount() {
        this.overdraftFeeDiscountCoefficient = DEFAULT_OVERDRAFT_FEE_DISCOUNT_COEFFICIENT;
    }

    public static String getIbanDaysOverdrawnString(Account account) {
        return "Account: IBAN: " + account.getIban() + ", Days Overdrawn: " + account.getDaysOverdrawn();
    }

    public static String getIbanMoneyString(Account account) {
        return "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney();
    }

    @Override
    public double bankcharge() {
        double result = BASE_BANKCHARGE;

        result += overdraftCharge();

        return result;
    }
}
