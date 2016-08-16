package chap3;

import lambdasinaction.chap1.Apple;
import lambdasinaction.chap1.Fruit;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MethodReferenceTests {

  @Test
  public void shouldSortViaLambda() {
    List<String> str = Arrays.asList("a", "b", "A", "B");

    str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
    assertThat(str, is(Arrays.asList("a", "A", "b", "B")));

    str.sort((s1, s2) -> s1.compareTo(s2));
    assertThat(str, is(Arrays.asList("A", "B", "a", "b")));
  }

  @Test
  public void shouldSortViaMethodReference() {
    List<String> str = Arrays.asList("a", "b", "A", "B");

    str.sort(String::compareToIgnoreCase);
    assertThat(str, is(Arrays.asList("a", "A", "b", "B")));

    str.sort(String::compareTo);
    assertThat(str, is(Arrays.asList("A", "B", "a", "b")));
  }

  @Test
  public void shouldConstructDefaultAppleViaConstructorReference() {
    Supplier<Apple> c1 = Apple::new;

    assertThat(c1.get(), is(new Apple(Apple.DEFAULT_WEIGHT, Apple.DEFAULT_COLOUR)));
  }

  @Test
  public void shouldConstructAppleWithGivenWeightViaConstructorReference() {
    IntFunction<Apple> c2 = Apple::new;

    Apple a2 = c2.apply(110);

    assertThat(a2, is(new Apple(110, Apple.DEFAULT_COLOUR)));
  }

  public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
    List<Apple> result = new ArrayList<>();
    for (Integer i : list) {
      result.add(f.apply(i));
    }
    return result;
  }

  @Test
  public void shouldConstructListOfApplesViaConstructorReferences() {
    List<Integer> weights = Arrays.asList(7, 3, 4, 10);
    List<Apple> expectedApples = Arrays.asList(
        new Apple(7, Apple.DEFAULT_COLOUR), new Apple(3, Apple.DEFAULT_COLOUR),
        new Apple(4, Apple.DEFAULT_COLOUR), new Apple(10, Apple.DEFAULT_COLOUR));

    assertThat(map(weights, Apple::new), is(expectedApples));
  }

  @Test
  public void shouldConstructAppleWithGivenWeightAndColourViaConstructorReference() {
    BiFunction<Integer, String, Apple> c2 = Apple::new;
    assertThat(c2.apply(110, "red"), is(new Apple(110, "red")));
  }

  public Fruit giveMeFruit(String fruit, Integer weight) {
    Map<String, Function<Integer, Fruit>> map = new HashMap<>();
    map.put("apple", Apple::new);
    map.put("orange", Orange::new);

    return map.get(fruit.toLowerCase()).apply(weight);
  }

  @Test
  public void shouldConstructDifferentFruits() {
    assertThat(giveMeFruit("apple", 20), is(new Apple(20, Apple.DEFAULT_COLOUR)));
    assertThat(giveMeFruit("orange", 15), is(new Orange(15)));
  }

  /*
  You saw how to transform zero-, one-, and two-argument constructors into constructor
  references. What would you need to do in order to use a constructor reference for a
  three-argument constructor such as Color(int, int, int)?
   */

  @Test
  public void shouldConstructThreeArgumentClassFromConstructorReference() {
    TriFunction<Integer, Integer, Integer, Colour> pallette = Colour::new;
    assertThat(pallette.apply(20, 40, 60), is(new Colour(20, 40, 60)));
  }
}