package lambdasinaction.chap6;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class GroupingTransactions {

    public static final Transaction TX_EUR_1 = new Transaction(Currency.EUR, 1500.0);
    public static final Transaction TX_USD_1 = new Transaction(Currency.USD, 2300.0);
    public static final Transaction TX_GBP_1 = new Transaction(Currency.GBP, 9900.0);
    public static final Transaction TX_EUR_2 = new Transaction(Currency.EUR, 1100.0);
    public static final Transaction TX_JPY_1 = new Transaction(Currency.JPY, 7800.0);
    public static final Transaction TX_CHF_1 = new Transaction(Currency.CHF, 6700.0);
    public static final Transaction TX_EUR_3 = new Transaction(Currency.EUR, 5600.0);
    public static final Transaction TX_USD_2 = new Transaction(Currency.USD, 4500.0);
    public static final Transaction TX_CHF_2 = new Transaction(Currency.CHF, 3400.0);
    public static final Transaction TX_GBP_2 = new Transaction(Currency.GBP, 3200.0);
    public static final Transaction TX_USD_3 = new Transaction(Currency.USD, 4600.0);
    public static final Transaction TX_JPY_2 = new Transaction(Currency.JPY, 5700.0);
    public static final Transaction TX_EUR_4 = new Transaction(Currency.EUR, 6800.0);

    public static List<Transaction> transactions = Arrays.asList(TX_EUR_1,
        TX_USD_1, TX_GBP_1, TX_EUR_2, TX_JPY_1, TX_CHF_1, TX_EUR_3, TX_USD_2, TX_CHF_2,
        TX_GBP_2, TX_USD_3, TX_JPY_2, TX_EUR_4);

    public static Map<Currency, List<Transaction>> groupTransactionsByCurrencyImperatively() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                    transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

        return transactionsByCurrencies;
    }

    public static Map<Currency, List<Transaction>>  groupTransactionsByCurrencyFunctionally() {
        Map<Currency, List<Transaction>> transactionsByCurrencies =
            transactions.stream().collect(groupingBy(Transaction::getCurrency));
        return transactionsByCurrencies;
    }
}