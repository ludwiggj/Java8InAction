package lambdasinaction.chap14;

import org.junit.jupiter.api.Test;

import static lambdasinaction.chap14.Combinators.repeat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CombinatorsTest {

  @Test
  public void shouldCombine() {
    assertThat(repeat(3, (Integer x) -> 2 * x).apply(10), is(80));
    assertThat(repeat(1, (Integer x) -> 2 * x).apply(10), is(20));
  }
}