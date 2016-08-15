package lambdasinaction.chap6;

import java.util.Objects;

public class Transaction {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.value, value) == 0 &&
            currency == that.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, value);
    }

    private final Currency currency;
    private final double value;

    public Transaction(Currency currency, double value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return currency + " " + value;
    }
}
