public class PersonStringUtils {

    private PersonStringUtils() {
        // This class is not expected to be instantiated.
    }

    public static String toFullNameDaysOverdrawnString(Person person) {
        return person.getFullName()
            + AccountStringUtils.toIbanDaysOverdrawnString(person.getAccount());
    }

    public static String toPersonFullNameIbanMoneyString(Person person) {
        return person.getFullName()
            + AccountStringUtils.toIbanMoneyString(person.getAccount());
    }
}
