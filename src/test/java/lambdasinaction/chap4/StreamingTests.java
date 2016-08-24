package lambdasinaction.chap4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap4.Dish.Type.FISH;
import static lambdasinaction.chap4.Dish.Type.MEAT;
import static lambdasinaction.chap4.Dish.Type.OTHER;
import static lambdasinaction.chap4.Restaurant.*;
import static lambdasinaction.chap4.Streaming.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.expectThrows;

@DisplayName("Streaming tests")
public class StreamingTests {

  private static List<String> expectedLowCaloricDishNames =
      Arrays.asList(SEASON_FRUIT, RICE)
          .stream()
          .map(Dish::getName)
          .collect(toList());

  private static List<String> expectedHighCaloricDishNames =
      Arrays.asList(PORK, BEEF, CHICKEN)
                .stream()
                .map(Dish::getName)
                .collect(toList());

  @Test
  @DisplayName("Get sorted list of names of low caloric dishes using idomatic Java7")
  public void shouldGetLowCaloricDishesNamesInJava7(){
    assertThat(getSortedLowCaloricDishesNamesInJava7(Restaurant.menu), is(expectedLowCaloricDishNames));
  }

  @Test
  @DisplayName("Get sorted list of names of low caloric dishes using idomatic Java8")
  public void shouldGetLowCaloricDishesNamesInJava8() {
    assertThat(getSortedLowCaloricDishesNamesInJava8(Restaurant.menu), is(expectedLowCaloricDishNames));
  }

  @Test
  @DisplayName("Get three most caloric dishes")
  public void shouldGetThreeHighCaloricDishNames() {
    assertThat(getThreeHighCaloricDishNames(Restaurant.menu), is(expectedHighCaloricDishNames));
  }

  @Test
  @DisplayName("Get dishes by type")
  public void shouldGetDishesByType() {
    Map<Dish.Type, List<Dish>> expectedResult = new HashMap<Dish.Type, List<Dish>>() {{
      put(FISH, new ArrayList<Dish>() {{ add(PRAWNS); add(SALMON); }});
      put(OTHER, new ArrayList<Dish>() {{ add(FRENCH_FRIES); add(RICE); add(SEASON_FRUIT); add(PIZZA); }});
      put(MEAT, new ArrayList<Dish>() {{ add(PORK); add(BEEF); add(CHICKEN); }});
    }};

    assertThat(getDishesByType(Restaurant.menu), is(expectedResult));
  }

  @Test
  @DisplayName("Can only consume stream once")
  public void canConsumeStreamOnlyOnce() {
    List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");

    Stream<String> s = names.stream();

    final String[] result = {""};

    s.forEach(x -> result[0] += x);

    assertThat(result[0], is("Java8LambdasInAction"));

    expectThrows(IllegalStateException.class, () -> {
      s.forEach(System.out::println);
    });
  }
}