import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Currency;

@Data
@AllArgsConstructor
public class Money {

    private Currency currency;

    private double money;
}
