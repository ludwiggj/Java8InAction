package lambdasinaction.chap11.v1;

import lambdasinaction.chap11.v1.AsyncShop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AsyncShopTest {

  @DisplayName("Get async price")
  @Test
  public void shouldReturnAsyncPrice() throws ExecutionException, InterruptedException {
    long start = System.nanoTime();

    AsyncShop shop = new AsyncShop("BestShop");
    Future<Double> futurePrice = shop.getPrice("my favorite product");

    long invocationTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.println("Invocation returned after " + invocationTime + " msecs");

    // Do some more tasks, like querying other shops
    doSomethingElse();

    // while the price of the product is being calculated
    // NOTE: get method blocks!
    double price = futurePrice.get();
    assertThat(price, is(greaterThan(0.0)));

    long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.printf("Price %.2f returned after %d msecs", price, retrievalTime);
  }

  private static void doSomethingElse() {
    System.out.println("Doing something else...");
  }

  @DisplayName("Get async price fails take 1")
  @Test
  public void shouldReturnExceptionWhenGetPrice() {
    AsyncShop shop = new AsyncShop("BestShop", true);
    Future<Double> futurePrice = shop.getPrice("my favorite product");

    ExecutionException rte = assertThrows(ExecutionException.class,
        () -> futurePrice.get()
    );

    assertThat(rte.getMessage(), is("java.lang.RuntimeException: product not available"));
  }

  @DisplayName("Get async price fails take 2")
  @Test
  public void shouldReturnExceptionWhenGetPrice2() {
    AsyncShop shop = new AsyncShop("BestShop", true);
    Future<Double> futurePrice = shop.getPrice2("my favorite product");

    ExecutionException rte = assertThrows(ExecutionException.class,
        () -> futurePrice.get()
    );

    assertThat(rte.getMessage(), is("java.lang.RuntimeException: product not available"));
  }
}