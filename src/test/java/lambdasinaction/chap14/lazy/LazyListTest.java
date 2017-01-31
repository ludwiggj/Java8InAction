package lambdasinaction.chap14.lazy;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static lambdasinaction.chap14.lazy.LazyList.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LazyListTest {

  @Test
  public void shouldGetFirstThreeNumbers() {
    LazyList<Integer> numbers = LazyList.from(2);

    assertThat(numbers.head(), is(2));
    assertThat(numbers.tail().head(), is(3));
    assertThat(numbers.tail().tail().head(), is(4));
  }

  @Test
  public void shouldGetFirstThreePrimeNumbers() {
    LazyList<Integer> numbers = LazyList.from(2);

    assertThat(primes(numbers).head(), is(2));
    assertThat(primes(numbers).tail().head(), is(3));
    assertThat(primes(numbers).tail().tail().head(), is(5));
  }

  @Disabled
  @Test
  // this will run until terminated
  public void printAllShouldRunUntilTerminated() {
    printAll(primes(from(2)));
  }

  @Disabled
  @Test
  // this will run until a stackoverflow occur because Java does not support tail call elimination
  public void recursivePrintAllShouldCauseStackOverflow() {
    printAllRecursive(primes(from(2)));
  }

  @Test
  public void willCallTailEveryTime() {
    LazyList<Integer> numbers = LazyList.fromWithDebug(2);

    int tenthValue = numbers.tail().tail().tail().tail().tail().tail().tail().tail().tail().head();
    System.out.println("Tenth value = [" + tenthValue + "]");

    int fifthValue = numbers.tail().tail().tail().tail().head();
    System.out.println("Fifth value = [" + fifthValue + "]");
  }
}