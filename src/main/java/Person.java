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

    public String getFullName() {
        return getName() + " " + getSurname() + " ";
    }
}
