package lambdasinaction.chap4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap4.Dish.Type.FISH;
import static lambdasinaction.chap4.Dish.Type.MEAT;
import static lambdasinaction.chap4.Dish.Type.OTHER;
import static lambdasinaction.chap4.Streaming.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Streaming tests")
public class StreamingTests {

  private static List<String> expectedLowCaloricDishNames =
      Arrays.asList(Dish.SEASON_FRUIT, Dish.RICE)
          .stream()
          .map(Dish::getName)
          .collect(toList());

  private static List<String> expectedHighCaloricDishNames =
      Arrays.asList(Dish.PORK, Dish.BEEF, Dish.CHICKEN)
                .stream()
                .map(Dish::getName)
                .collect(toList());

  @Test
  @DisplayName("Get sorted list of names of low caloric dishes using idomatic Java7")
  public void shouldGetLowCaloricDishesNamesInJava7(){
    assertThat(getSortedLowCaloricDishesNamesInJava7(Restaurant.MENU), is(expectedLowCaloricDishNames));
  }

  @Test
  @DisplayName("Get sorted list of names of low caloric dishes using idomatic Java8")
  public void shouldGetLowCaloricDishesNamesInJava8() {
    assertThat(getSortedLowCaloricDishesNamesInJava8(Restaurant.MENU), is(expectedLowCaloricDishNames));
  }

  @Test
  @DisplayName("Get three most caloric dishes")
  public void shouldGetThreeHighCaloricDishNames() {
    assertThat(getThreeHighCaloricDishNames(Restaurant.MENU), is(expectedHighCaloricDishNames));
  }

  @Test
  @DisplayName("Get dishes by type")
  public void shouldGetDishesByType() {
    Map<Dish.Type, List<Dish>> expectedResult = new HashMap<Dish.Type, List<Dish>>() {{
      put(FISH, new ArrayList<Dish>() {{ add(Dish.PRAWNS); add(Dish.SALMON); }});
      put(OTHER, new ArrayList<Dish>() {{ add(Dish.FRENCH_FRIES); add(Dish.RICE); add(Dish.SEASON_FRUIT); add(Dish.PIZZA); }});
      put(MEAT, new ArrayList<Dish>() {{ add(Dish.PORK); add(Dish.BEEF); add(Dish.CHICKEN); }});
    }};

    assertThat(getDishesByType(Restaurant.MENU), is(expectedResult));
  }

  @Test
  @DisplayName("Can only consume stream once")
  public void canConsumeStreamOnlyOnce() {
    List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");

    Stream<String> s = names.stream();

    final String[] result = {""};

    s.forEach(x -> result[0] += x);

    assertThat(result[0], is("Java8LambdasInAction"));

    assertThrows(IllegalStateException.class, () -> {
      s.forEach(System.out::println);
    });
  }
}