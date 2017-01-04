package lambdasinaction.chap11.v1;

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

  @Nested
  @DisplayName("In sequence")
  class InSequence {
    @Test
    @DisplayName("Four shops")
    public void shouldReturnPricesInSequenceFourShops() {
      List<String> prices = execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S"));
      assertThat(prices.size(), is(greaterThan(0)));
    }

    @Test
    @DisplayName("Five shops")
    public void shouldReturnPricesInSequenceFiveShops() {
      List<String> prices = execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S", 5));
      assertThat(prices.size(), is(greaterThan(0)));
    }
  }

  @Nested
  @DisplayName("In parallel")
  class InParallel {
    @Test
    @DisplayName("Four shops")
    public void shouldReturnPricesInParallelFourShops() {
      List<String> prices = execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S"));
      assertThat(prices.size(), is(greaterThan(0)));
    }

    @Test
    @DisplayName("Five shops")
    public void shouldReturnPricesInParallelFiveShops() {
      List<String> prices = execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S", 5));
      assertThat(prices.size(), is(greaterThan(0)));
    }

    @Test
    @DisplayName("Nine shops")
    public void shouldReturnPricesInParallelNineShops() {
      List<String> prices = execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S", 9));
      assertThat(prices.size(), is(greaterThan(0)));
    }
  }

  @Nested
  @DisplayName("Asynchronously")
  class Asynchronously {
    @Test
    @DisplayName("Four shops")
    public void shouldReturnPricesAsynchronouslyFourShops() {
      List<String> prices = execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("myPhone27S"));
      assertThat(prices.size(), is(greaterThan(0)));
    }

    @Test
    @DisplayName("Five shops")
    public void shouldReturnPricesAsynchronouslyFiveShops() {
      List<String> prices = execute("composed CompletableFuture",
          () -> bestPriceFinder.findPricesFuture("myPhone27S", 5)
      );
      assertThat(prices.size(), is(greaterThan(0)));
    }

    @Test
    @DisplayName("Nine shops")
    public void shouldReturnPricesAsynchronouslyNineShops() {
      List<String> prices = execute("composed CompletableFuture",
          () -> bestPriceFinder.findPricesFuture("myPhone27S", 9)
      );
      assertThat(prices.size(), is(greaterThan(0)));
    }
  }

  @Nested
  @DisplayName("Asynchronously with custom executor")
  class AsynchronouslyWithCustomExecutor {
    @Test
    @DisplayName("Five shops")
    public void shouldReturnPricesAsynchronouslyCustomExecutorFiveShops() {
      List<String> prices = execute("composed CompletableFuture",
          () -> bestPriceFinder.findPricesFutureWithCustomExecutor("myPhone27S", 5)
      );
      assertThat(prices.size(), is(greaterThan(0)));
    }

    @Test
    @DisplayName("Nine shops")
    public void shouldReturnPricesAsynchronouslyCustomExecutorNineShops() {
      List<String> prices = execute("composed CompletableFuture",
          () -> bestPriceFinder.findPricesFutureWithCustomExecutor("myPhone27S", 9)
      );
      assertThat(prices.size(), is(greaterThan(0)));
    }
  }

  @Test
  @DisplayName("Prices in USD Java 7")
  public void shouldReturnPricesInUSDJava7() {
    List<String> prices = execute("prices in USD", () -> bestPriceFinder.findPricesInUSDJava7("myPhone27S"));
    assertThat(prices.size(), is(greaterThan(0)));
  }

  @Test
  @DisplayName("Price in USD")
  public void shouldReturnPriceInUSD() {
    List<String> prices = execute("prices in USD", () -> bestPriceFinder.findPriceInUSD("myPhone27S"));
    assertThat(prices.size(), is(greaterThan(0)));
  }

  @Test
  @DisplayName("Prices in USD")
  public void shouldReturnPricesInUSD() {
    List<String> prices = execute("prices in USD", () -> bestPriceFinder.findPricesInUSD("myPhone27S"));
    assertThat(prices.size(), is(greaterThan(0)));
  }

  @Test
  @DisplayName("Prices in USD2")
  public void shouldReturnPricesInUSD2() {
    List<String> prices = execute("prices in USD v2", () -> bestPriceFinder.findPricesInUSD2("myPhone27S"));
    assertThat(prices.size(), is(greaterThan(0)));
  }

  @Test
  @DisplayName("Prices in USD3")
  public void shouldReturnPricesInUSD3() {
    List<String> prices = execute("prices in USD v3", () -> bestPriceFinder.findPricesInUSD3("myPhone27S"));
    assertThat(prices.size(), is(greaterThan(0)));
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