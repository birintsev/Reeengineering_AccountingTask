import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    @Test
    public void testBankchargePremiumLessThanAWeek() {
        Account account = getPremiumAccount(5);
        assertThat(account.bankcharge(), is(14.5));
    }

    @Test
    public void testBankchargePremiumMoreThanAWeek() {
        Account account = getPremiumAccount(9);
        assertThat(account.bankcharge(), is(16.5));
    }

    @Test
    public void testOverdraftFeePremium() {
        Account account = getPremiumAccount(9);
        assertThat(account.overdraftFee(), is(0.10));
    }

    @Test
    public void testOverdraftFeeNotPremium() {
        Account account = getNormalAccount();
        assertThat(account.overdraftFee(), is(0.20));
    }

    @Test
    public void testPrintCustomer() {
        Account account = getNormalAccount();
        Person person = new Person("xxx@mail.com", account, "xxx", "xxx");
        account.setCustomer(person);
        assertThat(account.printCustomer(), is("xxx xxx@mail.com"));
    }

    private Account getNormalAccount() {
        return new Account(AccountType.NORMAL, 9);
    }

    private Account getPremiumAccount(int daysOverdrawn) {
        return new Account(AccountType.PREMIUM, daysOverdrawn);
    }
}
