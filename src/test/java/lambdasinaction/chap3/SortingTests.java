package lambdasinaction.chap3;

import lambdasinaction.chap1.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;

import static java.util.Comparator.comparing;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SortingTests {
  private List<Apple> inventory;
  private List<Apple> sortedApples =
      Arrays.asList(new Apple(80, "green"), new Apple(120, "red"), new Apple(155, "green"), new Apple(160, "red"));

  static class AppleComparator implements Comparator<Apple> {
    public int compare(Apple a1, Apple a2) {
      return a1.getWeight().compareTo(a2.getWeight());
    }
  }

  @BeforeEach
  public void setUp() {
    inventory = Arrays.asList(
        new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"), new Apple(160, "red")
    );
  }

  @Test
  public void shouldSortApples1() {
    Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
    inventory.sort(c);
    assertThat(inventory, is(sortedApples));
  }

  @Test
  public void shouldSortApples2() {
    inventory.sort(new AppleComparator());
    assertThat(inventory, is(sortedApples));
  }

  @Test
  public void shouldSortApples3() {
    inventory.sort(new Comparator<Apple>() {
      public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
      }
    });
    assertThat(inventory, is(sortedApples));
  }

  @Test
  public void shouldSortApplesUsingLambda() {
    inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
    assertThat(inventory, is(sortedApples));
  }

  @Test
  public void shouldSortApplesUsingComparing() {
    inventory.sort(comparing(a -> a.getWeight()));
    assertThat(inventory, is(sortedApples));
  }

  @Test
  public void shouldSortApplesUsingMethodReference() {
    inventory.sort(comparing(Apple::getWeight));
    assertThat(inventory, is(sortedApples));
  }

  @Test
  public void shouldSortApplesIntoReverseOrderUsingMethodReference() {
    List<Apple> reverseSortedApples =
        Arrays.asList(new Apple(160, "red"), new Apple(155, "green"), new Apple(120, "red"), new Apple(80, "green"));

    inventory.sort(comparing(Apple::getWeight).reversed());
    assertThat(inventory, is(reverseSortedApples));
  }

  @Test
  public void shouldSortApplesIntoReverseOrderByColourThenWeightUsingMethodReference() {
    List<Apple> reverseSortedApples =
        Arrays.asList(new Apple(120, "red"), new Apple(160, "red"), new Apple(80, "green"), new Apple(155, "green"));

    inventory.sort(comparing(Apple::getColour).reversed().thenComparing(Apple::getWeight));
    assertThat(inventory, is(reverseSortedApples));
  }

  @Test
  public void shouldCompareApplesUsingBiFunction() {
    BiFunction<Apple, Apple, Integer> bif = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());

    assertThat(bif.apply(inventory.get(0), inventory.get(1)), is(-1));
  }

  @Test
  public void shouldCompareApplesUsingToIntBiFunction() {
    ToIntBiFunction<Apple, Apple> bif = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());

    assertThat(bif.applyAsInt(inventory.get(0), inventory.get(1)), is(-1));
  }

  public static List<Apple> filter(List<Apple> inventory, Predicate<Apple> p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  static Predicate<Apple> redApple = a -> a.getColour() == "red";
  static Predicate<Apple> greenApple = a -> a.getColour() == "green";
  static Predicate<Apple> heavy = a -> a.getWeight() > 150;

  @Test
  public void shouldFilterRedApples() throws Exception {
    assertThat(filter(inventory, redApple), is(Arrays.asList(new Apple(120, "red"), new Apple(160, "red"))));
  }

  @Test
  public void shouldFilterNonRedApples() throws Exception {
    Predicate<Apple> notRedApple = redApple.negate();

    assertThat(filter(inventory, notRedApple), is(Arrays.asList(new Apple(80, "green"), new Apple(155, "green"))));
  }

  @Test
  public void shouldFilterRedAndHeavyApples() throws Exception {
    Predicate<Apple> redAndHeavyApple = redApple.and(heavy);

    assertThat(filter(inventory, redAndHeavyApple), is(Arrays.asList(new Apple(160, "red"))));
  }

  @Test
  public void shouldFilterRedAndHeavyOrJustGreenApples() throws Exception {
    Predicate<Apple> redAndHeavyApple = redApple.and(heavy);

    Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(heavy).or(greenApple);

    assertThat(filter(inventory, redAndHeavyAppleOrGreen), is(Arrays.asList(
        new Apple(80, "green"), new Apple(155, "green"), new Apple(160, "red")))
    );
  }
}