import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Customer {

    private String name;

    @Setter
    private String email;

    @Getter(AccessLevel.PROTECTED)
    private Account account;

    public Customer(String name, String email, Account account) {
        this.name = name;
        this.email = email;
        this.account = account;
    }

    @Override
    public String toString() {
        return getName() + " " + getEmail();
    }
}
