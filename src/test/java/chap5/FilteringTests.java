package chap5;

import lambdasinaction.chap4.Dish;
import lambdasinaction.chap4.Restaurant;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap5.Filtering.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FilteringTests {

  @Test
  public void shouldFilterVeggieDishes() {
    List<Dish> expectedVegetarianDishes =
        Arrays.asList(Restaurant.FRENCH_FRIES, Restaurant.RICE, Restaurant.SEASON_FRUIT, Restaurant.PIZZA);

    assertThat(getVegetarianDishes(), is(expectedVegetarianDishes));
  }

  @Test
  public void shouldFilterUniqueEvenNumbers() {
    assertThat(filterEvenNumbers(), is(Arrays.asList(2, 4)));
  }

  @Test
  public void shouldGetFirstThreeDishesOver300Calories() {
    List<Dish> expectedFirstThreeDishes = Arrays.asList(Restaurant.PORK, Restaurant.BEEF, Restaurant.CHICKEN);

    assertThat(getFirstThreeDishesOver300Calories(), is(expectedFirstThreeDishes));
  }

  @Test
  public void shouldGetAllButFirstTwoDishesOver300Calories() {
    List<Dish> expectedDishes = Arrays.asList(
        Restaurant.CHICKEN, Restaurant.FRENCH_FRIES, Restaurant.RICE, Restaurant.PIZZA, Restaurant.PRAWNS, Restaurant.SALMON);

    assertThat(getAllButFirstTwoDishesOver300Calories(), is(expectedDishes));
  }

  @Test
  public void shouldGetFirstTwoMeatDishes() {
    List<Dish> expectedFirstTwoMeatDishes = Arrays.asList(Restaurant.PORK, Restaurant.BEEF);

    assertThat(getFirstTwoMeatDishes(), is(expectedFirstTwoMeatDishes));
  }
}
