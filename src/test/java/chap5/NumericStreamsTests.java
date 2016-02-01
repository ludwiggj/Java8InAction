package chap5;

import lambdasinaction.chap4.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static chap5.Utils.toListOfListsOfIntegers;
import static java.util.stream.Collectors.toList;
import static lambdasinaction.chap4.Dish.Type.FISH;
import static lambdasinaction.chap4.Restaurant.MYSTERY_FISH;
import static lambdasinaction.chap4.Restaurant.NOT_VEGETARIAN;
import static lambdasinaction.chap5.NumericStreams.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NumericStreamsTests {

  public static final List<Dish> EXPECTED_DISHES = Arrays.asList(
      new Dish(MYSTERY_FISH, NOT_VEGETARIAN, 200, FISH),
      new Dish(MYSTERY_FISH, NOT_VEGETARIAN, 400, FISH),
      new Dish(MYSTERY_FISH, NOT_VEGETARIAN, 600, FISH)
  );

  @Test
  public void shouldGetMenuTotalCalories() {
    assertThat(getMenuTotalCalories(), is(4300));
  }

  @Test
  public void shouldGetMostCalorificDishInMenu() {
    assertThat(getMostCalorificDishInMenu().getAsInt(), is(800));
  }

  @Test
  public void shouldGetDishesOfIncreasingCalorificValue() {
    assertThat(getDishesOfIncreasingCalorificValue(), is(EXPECTED_DISHES));
  }

  @Test
  public void shouldGetDishesOfIncreasingCalorificValue2() {
    assertThat(getDishesOfIncreasingCalorificValue2(), is(EXPECTED_DISHES));
  }

  @Test
  public void shouldGetCountOfRange() {
    assertThat(getCountOfRange(), is(49L));
  }

  @Test
  public void shouldGetCountOfRangeClosed() {
    assertThat(getCountOfRangeClosed(), is(50L));
  }

  @Test
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

  private static List<List<Double>> toListOfListsOfDoubles(List<double[]> input) {
    return input.stream().map(
        arr -> Arrays.stream(arr)
            .boxed()
            .collect(toList())
    ).collect(toList());
  }

  @Test
  public void shouldCorrectlyIdentifyAPerfectSquare() {
    assertThat(isPerfectSquare(49), is(true));
  }

  @Test
  public void shouldCorrectlyIdentifyAnImperfectSquare() throws Exception {
    assertThat(isPerfectSquare(48), is(false));
  }
}