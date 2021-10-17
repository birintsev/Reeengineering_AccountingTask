import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Account {

    private static double BASE_BANKCHARGE = 4.5;

    // 1 means 100%
    private static int DEFAULT_OVERDRAFT_FEE_DISCOUNT_COEFFICIENT = 1;

    private String iban;

    private double money;

    private int daysOverdrawn;

    private String currency;

    private Customer customer;

    private double overdraftFee;

    private double overdraftFeeDiscount;

    private double overdraftFeeDiscountCoefficient;

    public Account() {
        this.overdraftFeeDiscountCoefficient =
            DEFAULT_OVERDRAFT_FEE_DISCOUNT_COEFFICIENT;
    }

    public double bankcharge() {
        double result = BASE_BANKCHARGE;

        result += overdraftCharge();

        return result;
    }

    /**
     * Performs a withdraw bank operation depending on the current balance.
     * If the balance is positive,
     * {@link #calculateMoneyAfterWithdraw just the sum is withdrawn},
     * else {@link #calculateMoneyAfterWithdrawCredit additional fee is kept
     * from the operation}.
     *
     * @param sum      the sum to be withdrawn.
     * @param currency the currency which should be used in the transaction.
     *
     * @see            #calculateMoneyAfterWithdraw
     * @see            #calculateMoneyAfterWithdrawCredit
     * */
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

    protected abstract String getAccountTypeName();

    protected abstract double overdraftCharge();

    private double calculateMoneyAfterWithdraw(double sum) {
        return getMoney() - sum;
    }

    private double calculateMoneyAfterWithdrawCredit(double sum) {
        return (getMoney() - sum)
            - (sum
            * getOverdraftFee()
            * getOverdraftFeeDiscount()
            * getOverdraftFeeDiscountCoefficient());
    }
}
