package chap6;

import lambdasinaction.chap6.Currency;
import lambdasinaction.chap6.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lambdasinaction.chap6.GroupingTransactions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupingTransactionsTests {

  public static final Map<Currency, List<Transaction>> expectedGroupedTxs = new HashMap<>();

  @BeforeEach
  public void setUp() {
    expectedGroupedTxs.put(Currency.EUR, Arrays.asList(TX_EUR_1, TX_EUR_2, TX_EUR_3, TX_EUR_4));
    expectedGroupedTxs.put(Currency.USD, Arrays.asList(TX_USD_1, TX_USD_2, TX_USD_3));
    expectedGroupedTxs.put(Currency.GBP, Arrays.asList(TX_GBP_1, TX_GBP_2));
    expectedGroupedTxs.put(Currency.JPY, Arrays.asList(TX_JPY_1, TX_JPY_2));
    expectedGroupedTxs.put(Currency.CHF, Arrays.asList(TX_CHF_1, TX_CHF_2));
  }

  @Test
  public void shouldGroupTransactionsImperatively() {
    assertThat(groupTransactionsByCurrencyImperatively(), is(expectedGroupedTxs));
  }

  @Test
  public void shouldGroupTransactionsFunctionally() {
    assertThat(groupTransactionsByCurrencyFunctionally(), is(expectedGroupedTxs));
  }
}