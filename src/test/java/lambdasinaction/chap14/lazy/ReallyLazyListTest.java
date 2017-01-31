package lambdasinaction.chap14.lazy;

import org.junit.jupiter.api.Test;

import static lambdasinaction.chap14.lazy.LazyList.primes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReallyLazyListTest {

  @Test
  public void shouldGetFirstThreeNumbers() {
    ReallyLazyList<Integer> numbers = ReallyLazyList.from(2);

    assertThat(numbers.head(), is(2));
    assertThat(numbers.tail().head(), is(3));
    assertThat(numbers.tail().tail().head(), is(4));
  }

  @Test
  public void shouldGetFirstThreePrimeNumbers() {
    ReallyLazyList<Integer> numbers = ReallyLazyList.from(2);

    assertThat(primes(numbers).head(), is(2));
    assertThat(primes(numbers).tail().head(), is(3));
    assertThat(primes(numbers).tail().tail().head(), is(5));
  }

  @Test
  public void willOnlyCallTailFirstTime() {
    ReallyLazyList<Integer> numbers = ReallyLazyList.fromWithDebug(2);

    int tenthValue = numbers.tail().tail().tail().tail().tail().tail().tail().tail().tail().head();
    System.out.println("Tenth value = [" + tenthValue + "]");

    int fifthValue = numbers.tail().tail().tail().tail().head();
    System.out.println("Fifth value = [" + fifthValue + "]");
  }
}