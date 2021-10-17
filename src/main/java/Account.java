import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Account {

    private static double BASE_BANKCHARGE = 4.5;

    private static int DEFAULT_OVERDRAFT_FEE_DISCOUNT_COEFFICIENT = 1; // 1 means 100%

    private String iban;

    private double money;

    private int daysOverdrawn;

    private String currency;

    private Customer customer;

    private double overdraftFee;

    private double overdraftFeeDiscount;

    private double overdraftFeeDiscountCoefficient;

    public Account() {
        this.overdraftFeeDiscountCoefficient = DEFAULT_OVERDRAFT_FEE_DISCOUNT_COEFFICIENT;
    }

    public double bankcharge() {
        double result = BASE_BANKCHARGE;

        result += overdraftCharge();

        return result;
    }

    abstract String getAccountTypeName();

    abstract double overdraftCharge();

    public void withdraw(double sum, String currency) {
        if (!getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        setMoney(
            getMoney() < 0
            ? calculateMoneyAfterWithdrawCredit(sum)
            : calculateMoneyAfterWithdraw(sum)
        );
    }

    public double calculateMoneyAfterWithdraw(double sum) {
        return getMoney() - sum;
    }

    public double calculateMoneyAfterWithdrawCredit(double sum) {
        return (getMoney() - sum)
            - (sum
                * getOverdraftFee()
                * getOverdraftFeeDiscount()
                * getOverdraftFeeDiscountCoefficient());
    }
}
