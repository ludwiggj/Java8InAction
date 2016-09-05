package lambdasinaction.chap6;

import lambdasinaction.chap4.Dish;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static lambdasinaction.chap4.Dish.*;
import static lambdasinaction.chap4.Dish.Type.*;
import static lambdasinaction.chap5.Filtering.getVegetarianDishes;
import static lambdasinaction.chap6.Partitioning.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PartitioningTests {

  @Test
  @DisplayName("Menu partitioned by vegetarian")
  public void shouldPartitionMenuByVegetarian() {

    Map<Boolean, List<Dish>> expectedDishes =
        new HashMap<Boolean, List<Dish>>() {{
          put(false, asList(PORK, BEEF, CHICKEN, PRAWNS, SALMON));
          put(true, asList(FRENCH_FRIES, RICE, SEASON_FRUIT, PIZZA));
        }};

    assertThat(partitionByVegetarian(), is(expectedDishes));
  }

  @Test
  @DisplayName("Vegetarian dishes")
  public void shouldGetVegetarianDishes() {
    List<Dish> vegetarianDishes = asList(FRENCH_FRIES, RICE, SEASON_FRUIT, PIZZA);

    assertAll("Vegetarian dishes",
        () -> assertThat(partitionByVegetarian().get(true), is(vegetarianDishes)),
        () -> assertThat(getVegetarianDishes(), is(vegetarianDishes))
    );
  }

  @Test
  @DisplayName("Vegetarian dishes by dish type")
  public void shouldGetVegetarianDishesByDishType() {
    Map<Boolean, Map<Dish.Type, List<Dish>>> expectedDishes =
        new HashMap<Boolean, Map<Dish.Type, List<Dish>>>() {{
          put(false, new HashMap<Dish.Type, List<Dish>>() {{
            put(MEAT, asList(PORK, BEEF, CHICKEN));
            put(FISH, asList(PRAWNS, SALMON));
          }});
          put(true, new HashMap<Dish.Type, List<Dish>>() {{
            put(OTHER, asList(FRENCH_FRIES, RICE, SEASON_FRUIT, PIZZA));
          }});
        }};

    assertThat(vegAndNonVegDishesByType(), is(expectedDishes));
  }

  @Test
  @DisplayName("Most calorific veggy and non veggy dishes")
  public void shouldGetMostCalorificVeggyAndNonVeggyDishes() {

    Map<Boolean, Dish> expectedDishes =
        new HashMap<Boolean, Dish>() {{
          put(false, PORK);
          put(true, PIZZA);
        }};

    assertThat(mostCaloricPartitionedByVegetarian(), is(expectedDishes));
  }

  @Test
  @DisplayName("Dishes partitioned by vegetarian then by 500 calorie test")
  public void shouldGetDishesOf500CaloriesOrMoreByVegetarian() {
    Map<Boolean, Map<Boolean, List<Dish>>> expectedDishes =
        new HashMap<Boolean, Map<Boolean, List<Dish>>>() {{
          put(false, new HashMap<Boolean, List<Dish>>() {{
            put(false, asList(CHICKEN, PRAWNS, SALMON));
            put(true, asList(PORK, BEEF));
          }});
          put(true, new HashMap<Boolean, List<Dish>>() {{
            put(false, asList(RICE, SEASON_FRUIT));
            put(true, asList(FRENCH_FRIES, PIZZA));
          }});
        }};

    assertThat(dishesOf500CaloriesOrMoreByVegetarian(), is(expectedDishes));
  }

  @Test
  @DisplayName("Number of veggy and non veggy dishes")
  public void shouldCountVeggyAndNonVeggyDishes() {

    Map<Boolean, Long> expectedCounts =
        new HashMap<Boolean, Long>() {{
          put(false, 5L);
          put(true, 4L);
        }};

    assertThat(countNumberOfVeggyAndNonVeggyDishes(), is(expectedCounts));
  }
}