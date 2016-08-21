package lambdasinaction.chap3;

import lambdasinaction.chap1.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static lambdasinaction.chap1.Apple.GREEN;
import static lambdasinaction.chap1.Apple.RED;
import static lambdasinaction.chap1.ExampleApples.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Filtering")
public class FilteringTests {

  // Manual, stripped back version of the java.util.function.Predicate<T> interface
  @FunctionalInterface
  interface BespokePredicate<T> {
    boolean test(T t);
  }

  @Nested
  @DisplayName("Filter with bespoke predicate")
  class FilterBespokePredicate {

    private <T> List<T> filter(List<T> list, BespokePredicate<T> p) {
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
      BespokePredicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();

      assertThat(filter(Arrays.asList("z", "", "a"), nonEmptyStringPredicate), is(Arrays.asList("z", "a")));
    }
  }

  @Nested
  @DisplayName("Filter apples")
  class FilterAppleTests {
    private List<Apple> inventory;

    private final Predicate<Apple> redApple = a -> a.getColour() == RED;
    private final Predicate<Apple> greenApple = a -> a.getColour() == GREEN;
    private final Predicate<Apple> heavy = a -> a.isHeavy();

    private <T> List<T> filter(List<T> list, Predicate<T> p) {
      List<T> results = new ArrayList<>();
      for (T s : list) {
        if (p.test(s)) {
          results.add(s);
        }
      }
      return results;
    }

    @BeforeEach
    public void setUp() {
      inventory = Arrays.asList(
          APPLE_GREEN_80, APPLE_GREEN_155, APPLE_RED_120, APPLE_RED_160
      );
    }

    @Test
    @DisplayName("Filter green apples")
    public void shouldFilterOutNonGreenApples() {
      assertThat(filter(inventory, (Apple a) -> GREEN.equals(a.getColour())), is(GREEN_APPLES));
    }

    @Test
    @DisplayName("Filter red apples")
    public void shouldFilterRedApples() {
      assertThat(filter(inventory, redApple), is(RED_APPLES));
    }

    @Test
    @DisplayName("Filter non-red apples")
    public void shouldFilterNonRedApples() {
      Predicate<Apple> notRedApple = redApple.negate();

      assertThat(filter(inventory, notRedApple), is(GREEN_APPLES));
    }

    @Test
    @DisplayName("Filter red and heavy apples")
    public void shouldFilterRedAndHeavyApples() {
      Predicate<Apple> redAndHeavyApple = redApple.and(heavy);

      assertThat(filter(inventory, redAndHeavyApple), is(Arrays.asList(APPLE_RED_160)));
    }

    @Test
    @DisplayName("Filter red and heavy apples or green apples")
    public void shouldFilterRedAndHeavyOrJustGreenApples() {
      Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(heavy).or(greenApple);

      assertThat(filter(inventory, redAndHeavyAppleOrGreen), is(
          Arrays.asList(APPLE_GREEN_80, APPLE_GREEN_155, APPLE_RED_160))
      );
    }
  }
}