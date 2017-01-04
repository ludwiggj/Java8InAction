package lambdasinaction.chap11.v2;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@DisplayName("Get prices")
public class BestPriceFinderTest {

  private BestPriceFinder bestPriceFinder;

  @BeforeEach
  public void setup() {
    bestPriceFinder = new BestPriceFinder();
  }

  @Test
  @DisplayName("Prices in sequence")
  public void shouldReturnPricesInSequence() {
    List<String> prices = execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S"));
    assertThat(prices.size(), is(greaterThan(0)));
  }

  @Test
  @DisplayName("Prices in parallel")
  public void shouldReturnPricesInParallel() {
    List<String> prices = execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S"));
    assertThat(prices.size(), is(greaterThan(0)));
  }

  @Test
  @DisplayName("Prices asynchronously")
  public void shouldReturnPricesAsynchronously() {
    List<String> prices = execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("myPhone27S"));
    assertThat(prices.size(), is(greaterThan(0)));
  }

  @Test
  @DisplayName("printPricesStream")
  public void testPrintPricesStream() {
    bestPriceFinder.printPricesStream("myPhone27S");
  }

  private static List<String> execute(String msg, Supplier<List<String>> s) {
    long start = System.nanoTime();
    List<String> result = s.get();
    System.out.println(result);
    long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println(msg + " done in " + duration + " msecs");
    return result;
  }
}