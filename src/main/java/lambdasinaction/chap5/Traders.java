package lambdasinaction.chap5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Traders {
  public static final Trader raoul = new Trader("Raoul", "Cambridge");
  public static final Trader mario = new Trader("Mario", "Milan");
  public static final Trader alan = new Trader("Alan", "Cambridge");
  public static final Trader brian = new Trader("Brian", "Cambridge");

  public static final Transaction txAlan1 = new Transaction(alan, 2012, 950);
  public static final Transaction txBrian1 = new Transaction(brian, 2011, 300);
  public static final Transaction txMario1 = new Transaction(mario, 2012, 710);
  public static final Transaction txMario2 = new Transaction(mario, 2012, 700);
  public static final Transaction txRaoul1 = new Transaction(raoul, 2012, 1000);
  public static final Transaction txRaoul2 = new Transaction(raoul, 2011, 400);

  public static final List<Transaction> transactions = Arrays.asList(
      txBrian1, txRaoul1, txRaoul2, txMario1, txMario2, txAlan1
  );

  public static List<Transaction> getAll2011TxsAndSortByAscendingValue() {
    return transactions.stream()
        .filter(tx -> tx.getYear() == 2011)
        .sorted(comparing(Transaction::getValue))
        .collect(toList());
  }

  public static List<String> getAllUniqueCities() {
    return transactions.stream()
        .map(tx -> tx.getTrader().getCity())
        .distinct()
        .sorted()
        .collect(toList());
  }

  public static List<Trader> getAllCambridgeTradersSortedByName() {
    return transactions.stream()
        .map(Transaction::getTrader)
        .distinct()
        .filter(trader -> trader.getCity().equals("Cambridge"))
        .sorted(comparing(Trader::getName))
        .collect(toList());
  }

  public static String getTradersNamesSortedAlphabetically() {
    return transactions.stream()
        .map(Transaction::getTrader)
        .map(Trader::getName)
        .distinct()
        .sorted()
        .reduce("", (s1, s2) -> s1 + s2);
  }

  public static boolean getTradersBasedInMilan() {
    return transactions.stream()
        .map(Transaction::getTrader)
        .anyMatch(t -> t.getCity().equals("Milan"));
  }

  public static boolean getTradersBasedInMilan2() {
    return transactions.stream()
        .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
  }

  public static List<Integer> getAllValuesFromTradersBasedInCambridge() {
    return getStreamOfAllValuesFromTradersBasedInCambridge().collect(toList());
  }

  public static Stream<Integer> getStreamOfAllValuesFromTradersBasedInCambridge() {
    return transactions.stream()
        .filter(tx -> "Cambridge".equals(tx.getTrader().getCity()))
        .map(Transaction::getValue);
  }

  public static Optional<Integer> getHighestTransactionValue() {
    return transactions.stream()
        .map(Transaction::getValue)
        .reduce(Integer::max);
  }

  public static Optional<Transaction> getTransactionOfSmallestValue() {
    return transactions.stream()
        .reduce((tx1, tx2) -> tx1.getValue() < tx2.getValue() ? tx1 : tx2);
  }

  public static Optional<Transaction> getTransactionOfSmallestValue2() {
    return transactions.stream().min(comparing(Transaction::getValue));
  }

  public static void main(String[] args) {
    getStreamOfAllValuesFromTradersBasedInCambridge().forEach(System.out::println);
  }
}