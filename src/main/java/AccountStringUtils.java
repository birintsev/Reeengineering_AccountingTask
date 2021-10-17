public class AccountStringUtils {

    private AccountStringUtils() {
        // This class is not expected to be instantiated.
    }

    public static String toIbanMoneyTypeString(Account account) {
        return "Account: IBAN: " + account.getIban()
            + ", Money: " + account.getBalance().getMoney()
            + ", Account type: " + account.getAccountTypeName();
    }

    public static String toIbanDaysOverdrawnString(Account account) {
        return "Account: IBAN: " + account.getIban()
            + ", Days Overdrawn: " + account.getDaysOverdrawn();
    }

    public static String toIbanMoneyString(Account account) {
        return "Account: IBAN: " + account.getIban()
            + ", Money: " + account.getBalance().getMoney();
    }
}
