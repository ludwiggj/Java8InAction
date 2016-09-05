package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap5.Filtering.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FilteringTests {

  @Test
  @DisplayName("Get vegetarian dishes")
  public void shouldFilterVeggieDishes() {
    List<Dish> expectedVegetarianDishes =
        Arrays.asList(Dish.FRENCH_FRIES, Dish.RICE, Dish.SEASON_FRUIT, Dish.PIZZA);

    assertThat(getVegetarianDishes(), is(expectedVegetarianDishes));
  }

  @Test
  @DisplayName("Get unique even numbers")
  public void shouldFilterUniqueEvenNumbers() {
    assertThat(filterEvenNumbers(), is(Arrays.asList(2, 4)));
  }

  @Test
  @DisplayName("Get first three dishes over 300 calories")
  public void shouldGetFirstThreeDishesOver300Calories() {
    List<Dish> expectedFirstThreeDishes = Arrays.asList(Dish.PORK, Dish.BEEF, Dish.CHICKEN);

    assertThat(getFirstThreeDishesOver300Calories(), is(expectedFirstThreeDishes));
  }

  @Test
  @DisplayName("Get all but first two dishes over 300 calories")
  public void shouldGetAllButFirstTwoDishesOver300Calories() {
    List<Dish> expectedDishes = Arrays.asList(
        Dish.CHICKEN, Dish.FRENCH_FRIES, Dish.RICE, Dish.PIZZA, Dish.PRAWNS, Dish.SALMON);

    assertThat(getAllButFirstTwoDishesOver300Calories(), is(expectedDishes));
  }

  @Test
  @DisplayName("Get first two meat dishes")
  public void shouldGetFirstTwoMeatDishes() {
    List<Dish> expectedFirstTwoMeatDishes = Arrays.asList(Dish.PORK, Dish.BEEF);

    assertThat(getFirstTwoMeatDishes(), is(expectedFirstTwoMeatDishes));
  }
}
