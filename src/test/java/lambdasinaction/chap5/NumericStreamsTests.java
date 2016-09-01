package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap4.Restaurant.*;
import static lambdasinaction.chap5.Utils.toListOfListsOfDoubles;
import static lambdasinaction.chap5.Utils.toListOfListsOfIntegers;
import static lambdasinaction.chap4.Dish.Type.FISH;
import static lambdasinaction.chap5.NumericStreams.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class NumericStreamsTests {

  public static final List<Dish> EXPECTED_DISHES = Arrays.asList(
      new Dish(MYSTERY_FISH, NOT_VEGETARIAN, 200, FISH),
      new Dish(MYSTERY_FISH, NOT_VEGETARIAN, 400, FISH),
      new Dish(MYSTERY_FISH, NOT_VEGETARIAN, 600, FISH)
  );

  @Test
  @DisplayName("Menu total calories")
  public void shouldGetMenuTotalCalories() {
    assertAll("Menu total calories",
        () -> assertThat(getMenuTotalCalories(MENU), is(4300)),
        () -> assertThat(getMenuTotalCalories(EMPTY_MENU), is(0))
    );
  }

  @Test
  @DisplayName("Most calorific dish")
  public void shouldGetMostCalorificDishInMenu() {
    assertAll("Most calorific dish",
        () -> assertThat(getMostCalorificDishInMenu(MENU).getAsInt(), is(800)),
        () -> assertThat(getMostCalorificDishInMenu(EMPTY_MENU).isPresent(), is(false))
    );
  }

  @Test
  @DisplayName("Most calorific dish or default")
  public void shouldGetMostCalorificDishInMenuOrDefault() {
    assertAll("Most calorific dish or default",
        () -> assertThat(getMostCalorificDishInMenuOrDefault(MENU), is(800)),
        () -> assertThat(getMostCalorificDishInMenuOrDefault(EMPTY_MENU), is(0))
    );
  }

  @Test
  @DisplayName("Dishes of increasing calorific value")
  public void shouldGetDishesOfIncreasingCalorificValue() {
    assertAll("Dishes of increasing calorific value",
        () -> assertThat(getDishesOfIncreasingCalorificValue(), is(EXPECTED_DISHES)),
        () -> assertThat(getDishesOfIncreasingCalorificValueBoxed(), is(EXPECTED_DISHES))
    );
  }

  @Test
  @DisplayName("Count of range")
  public void shouldGetCountOfRange() {
    assertThat(getCountOfRange(), is(49L));
  }

  @Test
  @DisplayName("Count of range closed")
  public void shouldGetCountOfRangeClosed() {
    assertThat(getCountOfRangeClosed(), is(50L));
  }

  @Test
  @DisplayName("Pythagorean triples")
  public void shouldGetPythagoreanTriples() {
    List<List<Integer>> expectedTrips = Arrays.asList(
        Arrays.asList(3, 4, 5),
        Arrays.asList(5, 12, 13),
        Arrays.asList(6, 8, 10),
        Arrays.asList(7, 24, 25),
        Arrays.asList(8, 15, 17)
    );

    List<List<Integer>> pythagoreanTriples = toListOfListsOfIntegers(getPythagoreanTriples());

    assertThat(pythagoreanTriples, is(expectedTrips));
  }

  @Test
  @DisplayName("Pythagorean triples improved")
  public void shouldGetPythagoreanTriplesBetter() {
    List<double[]> expectedTrips = Arrays.asList(
        new double[]{3, 4, 5},
        new double[]{5, 12, 13},
        new double[]{6, 8, 10},
        new double[]{7, 24, 25},
        new double[]{8, 15, 17}
    );

    List<double[]> pythagoreanTriples = getPythagoreanTriplesBetter();

    assertThat(toListOfListsOfDoubles(pythagoreanTriples), is(toListOfListsOfDoubles(expectedTrips)));
  }

  @Test
  @DisplayName("Perfect squares")
  public void shouldCorrectlyIdentifyAPerfectSquare() {
    assertAll("Perfect squares",
        () -> assertThat(isPerfectSquare(49), is(true)),
        () -> assertThat(isPerfectSquare(48), is(false))
    );
  }
}