public class Company extends Customer {

    private double companyOverdraftDiscount;

    public Company(
        String name,
        String email,
        Account account,
        double companyOverdraftDiscount
    ) {
        super(name, email, account);

        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    @Override
    protected double calculateAccountMoneyAfterWithdrawCredit(double sum) {
        Account account = getAccount();
        double moneyWillBeLeft;
        if (account.getType() == AccountType.PREMIUM) {
            moneyWillBeLeft = (account.getMoney() - sum) - sum * account.overdraftFee() * companyOverdraftDiscount / 2;
        } else {
            moneyWillBeLeft = (account.getMoney() - sum) - sum * account.overdraftFee() * companyOverdraftDiscount;
        }
        return moneyWillBeLeft;
    }
}
