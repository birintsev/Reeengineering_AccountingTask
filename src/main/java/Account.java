public interface Account {

    String getIban();

    void setIban(String iban);

    double getMoney();

    void setMoney(double sum);

    int getDaysOverdrawn();

    void setDaysOverdrawn(int daysOverdrawn);

    double getOverdraftFee();

    double getOverdraftFeeDiscount();

    void setOverdraftFeeDiscount(double overdraftFeeDiscount);

    double getOverdraftFeeDiscountCoefficient();

    void setOverdraftFeeDiscountCoefficient(double overdraftFeeDiscountCoefficient);

    String getCurrency();

    void setCurrency(String currency);

    Customer getCustomer();

    void setCustomer(Customer customer);

    double bankcharge();

    String getAccountTypeName();

    double overdraftCharge();

    default void withdraw(double sum, String currency) {
        if (!getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        setMoney(
            getMoney() < 0
            ? calculateMoneyAfterWithdrawCredit(sum)
            : calculateMoneyAfterWithdraw(sum)
        );
    }

    default double calculateMoneyAfterWithdraw(double sum) {
        return getMoney() - sum;
    }

    default double calculateMoneyAfterWithdrawCredit(double sum) {
        return (getMoney() - sum)
            - sum
                * getOverdraftFee()
                * getOverdraftFeeDiscount()
                * getOverdraftFeeDiscountCoefficient();
    }
}
