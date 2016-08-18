package lambdasinaction.chap3;

import lambdasinaction.chap1.Apple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap1.Apple.GREEN;
import static lambdasinaction.chap1.ExampleApples.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@FunctionalInterface
// Manual, stripped back version of the java.util.function.Predicate<T> interface
interface Predicate<T> {
  boolean test(T t);
}

@DisplayName("Filtering")
public class FilteringTests {

  private static <T> List<T> filter(List<T> list, Predicate<T> p) {
    List<T> results = new ArrayList<>();
    for (T s : list) {
      if (p.test(s)) {
        results.add(s);
      }
    }
    return results;
  }

  @Test
  @DisplayName("Filter out empty strings")
  public void shouldFilterOutEmptyStrings() {
    Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();

    assertThat(filter(Arrays.asList("z", "", "a"), nonEmptyStringPredicate), is(Arrays.asList("z", "a")));
  }

  @Test
  @DisplayName("Filter out non green apples")
  public void shouldFilterOutNonGreenApples() {
    // Filtering with lambdas
    List<Apple> inventory = Arrays.asList(APPLE_GREEN_80, APPLE_GREEN_155, APPLE_RED_120);

    assertThat(filter(inventory, (Apple a) -> GREEN.equals(a.getColour())), is(GREEN_APPLES));
  }
}