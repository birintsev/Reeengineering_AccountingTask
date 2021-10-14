public enum AccountType {

    PREMIUM("premium"), NORMAL("normal");

    private final String stringValue;

    AccountType(String stringValue) {
        this.stringValue = stringValue;
    }


    @Override
    public String toString() {
        return stringValue;
    }
}
