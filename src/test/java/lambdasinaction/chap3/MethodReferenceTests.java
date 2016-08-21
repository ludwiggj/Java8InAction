package lambdasinaction.chap3;

import lambdasinaction.chap1.Apple;
import lambdasinaction.chap1.Fruit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

import static lambdasinaction.chap1.Apple.*;
import static lambdasinaction.chap1.Apple.DEFAULT_WEIGHT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MethodReferenceTests {

  @Nested
  @DisplayName("Sorting")
  class Sorting {
    private final List<String> list = Arrays.asList("a", "b", "A", "B");
    private final List<String> sortedListIgnoringCase = Arrays.asList("a", "A", "b", "B");
    private final List<String> sortedList = Arrays.asList("A", "B", "a", "b");


    @Test
    @DisplayName("Sort via lambda")
    public void shouldSortViaLambda() {
      list.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
      assertThat(list, is(sortedListIgnoringCase));

      list.sort((s1, s2) -> s1.compareTo(s2));
      assertThat(list, is(sortedList));
    }

    @Test
    @DisplayName("Sort via method reference")
    public void shouldSortViaMethodReference() {
      list.sort(String::compareToIgnoreCase);
      assertThat(list, is(sortedListIgnoringCase));

      list.sort(String::compareTo);
      assertThat(list, is(sortedList));
    }
  }

  @Nested
  @DisplayName("Fruity constructors")
  class FruityConstructors {

    @Test
    @DisplayName("Construct default apple")
    public void shouldConstructDefaultAppleViaConstructorReference() {
      Supplier<Apple> c1 = Apple::new;
      Apple defaultApple = new Apple(DEFAULT_WEIGHT, DEFAULT_COLOUR);

      assertThat(c1.get(), is(defaultApple));
    }

    @Test
    @DisplayName("Construct apple with given weight")
    public void shouldConstructAppleWithGivenWeightViaConstructorReference() {
      IntFunction<Apple> c2 = Apple::new;

      Apple a2 = c2.apply(110);

      assertThat(a2, is(new Apple(110, DEFAULT_COLOUR)));
    }

    private List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
      List<Apple> result = new ArrayList<>();
      for (Integer i : list) {
        result.add(f.apply(i));
      }
      return result;
    }

    @Test
    @DisplayName("Construct lots of apples")
    public void shouldConstructListOfApplesViaConstructorReferences() {
      List<Integer> weights = Arrays.asList(7, 3, 4, 10);
      List<Apple> expectedApples = Arrays.asList(
          new Apple(7, DEFAULT_COLOUR), new Apple(3, DEFAULT_COLOUR),
          new Apple(4, DEFAULT_COLOUR), new Apple(10, DEFAULT_COLOUR));

      assertThat(map(weights, Apple::new), is(expectedApples));
    }

    @Test
    @DisplayName("Construct apple with given colour and weight")
    public void shouldConstructAppleWithGivenWeightAndColourViaConstructorReference() {
      BiFunction<Integer, String, Apple> c2 = Apple::new;
      assertThat(c2.apply(110, RED), is(new Apple(110, RED)));
    }

    private Fruit giveMeFruit(String fruit, Integer weight) {
      Map<String, Function<Integer, Fruit>> map = new HashMap<>();
      map.put("apple", Apple::new);
      map.put("orange", Orange::new);

      return map.get(fruit.toLowerCase()).apply(weight);
    }

    @Test
    @DisplayName("Construct different fruits")
    public void shouldConstructDifferentFruits() {
      assertAll("all fruits",
          () -> assertThat(giveMeFruit("apple", 20), is(new Apple(20, DEFAULT_COLOUR))),
          () -> assertThat(giveMeFruit("orange", 15), is(new Orange(15)))
      );
    }
  }

  /*
  You saw how to transform zero-, one-, and two-argument constructors into constructor
  references. What would you need to do in order to use a constructor reference for a
  three-argument constructor such as Color(int, int, int)?
   */

  @FunctionalInterface
  public interface TriFunction<T, U, V, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @param v the third function argument
     * @return the function result
     */
    R apply(T t, U u, V v);
  }

  @Test
  public void shouldConstructThreeArgumentClassFromConstructorReference() {
    TriFunction<Integer, Integer, Integer, Colour> pallette = Colour::new;
    assertThat(pallette.apply(20, 40, 60), is(new Colour(20, 40, 60)));
  }
}