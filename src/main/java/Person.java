import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person extends Customer {

    private String surname;

    public Person(String email, Account account, String name, String surname) {
        super(name, email, account);
        this.surname = surname;
    }

    public String printCustomerDaysOverdrawn() { // TODO: find more relevant class for this method (or make it a static function)
        return getFullName() + Account.getIbanDaysOverdrawnString(getAccount());
    }

    public String printCustomerMoney() { // TODO: find more relevant class for this method (or make it a static function)
        return getFullName() + Account.getIbanMoneyString(getAccount());
    }

    private String getFullName() {
        return getName() + " " + getSurname() + " ";
    }
}
