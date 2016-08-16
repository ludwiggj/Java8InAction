package chap3;

import lambdasinaction.chap1.Apple;
import lambdasinaction.chap3.Predicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap3.Lambdas.filter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FilteringTests {

  @Test
  public void shouldFilterOutEmptyStrings() throws Exception {
    Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
    List<String> listOfStrings = Arrays.asList("z", "", "a");

    assertThat(filter(listOfStrings, nonEmptyStringPredicate), is(Arrays.asList("z", "a")));
  }

  @Test
  public void shouldFilterOutNonGreenApples() throws Exception {
    // Filtering with lambdas
    List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
    List<Apple> expectedGreenApples = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"));

    assertThat(filter(inventory, (Apple a) -> "green".equals(a.getColour())), is(expectedGreenApples));
  }
}