package lambdasinaction.chap5.finance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static lambdasinaction.chap5.finance.Traders.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TradersTests {

  @Test
  @DisplayName("Q1) All transactions in the year 2011, sorted by value, low to high")
  public void shouldGetAll2011TxsAndSortByAscendingValue() {
    List<Transaction> expectedTxs = Arrays.asList(txBrian1, txRaoul2);

    assertThat(getAll2011TxsAndSortByAscendingValue(), is(expectedTxs));
  }

  @Test
  @DisplayName("Q2) All the unique cities where the traders work")
  public void shouldGetAllUniqueCities() {
    List<String> expectedCities = Arrays.asList("Cambridge", "Milan");

    assertAll("Unique cities",
        () -> assertThat(getAllUniqueCities(), is(expectedCities)),
        () -> assertThat(getAllUniqueCitiesTake2(), is(new HashSet(expectedCities)))
    );
  }

  @Test
  @DisplayName("Q3) All traders from Cambridge sorted by name")
  public void shouldGetAllCambridgeTradersSortedByName() {
    List<Trader> expectedCambridgeTraders = Arrays.asList(alan, brian, raoul);

    assertThat(getAllCambridgeTradersSortedByName(), is(expectedCambridgeTraders));
  }

  @Test
  @DisplayName("Q4) A string of all traders’ names, sorted alphabetically.")
  public void shouldGetTradersNamesSortedAlphabetically() {
    String expectedTradersNames = "AlanBrianMarioRaoul";

    assertAll("Traders names",
        () -> assertThat(getTradersNamesSortedAlphabetically(), is(expectedTradersNames)),
        () -> assertThat(getTradersNamesSortedAlphabeticallyTake2(), is(expectedTradersNames))
    );
  }

  @Test
  @DisplayName("Q5) Are any traders based in Milan?")
  public void shouldDetermineIfThereAreAnyTradersBasedInMilan() {
    assertAll("Milan traders",
        () -> assertThat(getTradersBasedInMilan(), is(true)),
        () -> assertThat(getTradersBasedInMilanTake2(), is(true))
    );
  }

  @Test
  @DisplayName("Q6) Get all transactions’ values from the traders living in Cambridge")
  public void shouldGetAllValuesFromTradersBasedInCambridge() {
    List<Integer> expectedTxValues = Arrays.asList(300, 1000, 400, 950);

    assertThat(getAllValuesFromTradersBasedInCambridge(), is(expectedTxValues));
  }

  @Test
  @DisplayName("Q7) Highest value of all the transactions")
  public void shouldGetHighestTransactionValue() {
    assertThat(getHighestTransactionValue().get(), is(1000));
  }

  @Test
  @DisplayName("Q8) Transaction with the smallest value")
  public void shouldGetTransactionOfSmallestValue() {
    assertAll("Min tx",
        () -> assertThat(getTransactionOfSmallestValue().get(), is(txBrian1)),
        () -> assertThat(getTransactionOfSmallestValueTake2().get(), is(txBrian1))
    );
  }
}