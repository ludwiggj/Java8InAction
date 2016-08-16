package chap5;

import lambdasinaction.chap5.Trader;
import lambdasinaction.chap5.Transaction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap5.Traders.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TradersTests {

  // Q1) Find all transactions in the year 2011 and sort them by value (small to high).
  @Test
  public void shouldGetAll2011TxsAndSortByAscendingValue() {
    List<Transaction> expectedTxs = Arrays.asList(txBrian1, txRaoul2);

    assertThat(getAll2011TxsAndSortByAscendingValue(), is(expectedTxs));
  }

  // Q2) What are all the unique cities where the traders work?
  @Test
  public void shouldGetAllUniqueCities() {
    List<String> expectedCities = Arrays.asList("Cambridge", "Milan");

    assertThat(getAllUniqueCities(), is(expectedCities));
  }

  // Q3 Find all traders from Cambridge and sort them by name.
  @Test
  public void shouldGetAllCambridgeTradersSortedByName() {
    List<Trader> expectedCambridgeTraders = Arrays.asList(alan, brian, raoul);

    assertThat(getAllCambridgeTradersSortedByName(), is(expectedCambridgeTraders));
  }

  // Q4 Return a string of all traders’ names sorted alphabetically.
  @Test
  public void shouldGetTradersNamesSortedAlphabetically() {
    String expectedTradersNames = "AlanBrianMarioRaoul";

    assertThat(getTradersNamesSortedAlphabetically(), is(expectedTradersNames));
  }

  // Q5 Are any traders based in Milan?
  @Test
  public void shouldDetermineIfThereAreAnyTradersBasedInMilan() {
    assertThat(getTradersBasedInMilan(), is(true));
  }

  @Test
  public void shouldDetermineIfThereAreAnyTradersBasedInMilan2() {
    assertThat(getTradersBasedInMilan2(), is(true));
  }

  // Q6 Print all transactions’ values from the traders living in Cambridge.
  @Test
  public void shouldGetAllValuesFromTradersBasedInCambridge() {
    List<Integer> expectedTxValues = Arrays.asList(300, 1000, 400, 950);

    assertThat(getAllValuesFromTradersBasedInCambridge(), is(expectedTxValues));
  }

  // Q7 What’s the highest value of all the transactions?
  @Test
  public void shouldGetHighestTransactionValue() {
    assertThat(getHighestTransactionValue().get(), is(1000));
  }

  // Q8 Find the transaction with the smallest value
  @Test
  public void shouldGetTransactionOfSmallestValue() {
    assertThat(getTransactionOfSmallestValue().get(), is(txBrian1));
  }

  @Test
  public void shouldGetTransactionOfSmallestValue2() {
    assertThat(getTransactionOfSmallestValue2().get(), is(txBrian1));
  }
}