package lambdasinaction.chap6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lambdasinaction.chap4.Dish.*;
import static lambdasinaction.chap4.Restaurant.MENU;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.expectThrows;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.NoSuchElementException;

import static lambdasinaction.chap6.ReducingAndSummarising.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SummarisingTests {

  private static final long EXPECTED_TOTAL_CALORIES = 4300;
  private static final long MENU_SIZE = MENU.size();
  private static final double EXPECTED_MENU_AVERAGE_CALORIES = (double) EXPECTED_TOTAL_CALORIES / MENU_SIZE;

  @Test
  @DisplayName("Count dishes")
  public void shouldCountNumberOfDishes() {
    assertAll("Count dishes",
        () -> assertThat(howManyDishes(), is(MENU_SIZE)),
        () -> assertThat(howManyDishesDirect(), is(MENU_SIZE))
    );
  }

  @Test
  @DisplayName("Most calorific dish")
  public void shouldFindMostCalorificDish() {
    assertAll("Most calorific dish",
        () -> assertThat(findMostCalorificDishViaMaxBy(), is(PORK)),
        () -> assertThat(findMostCalorificDishViaReducing(MENU), is(PORK)),
        () -> assertThat(findMostCalorificViaReducingExpanded(), is(PORK))
    );
  }

  @Test
  @DisplayName("Most calorific dish of empty list does not exist")
  public void shouldFindMostCalorificDishFromEmptyMenu() {
    expectThrows(NoSuchElementException.class, () -> {
      findMostCalorificDishViaReducing(new ArrayList<>());
    });
  }

  @Test
  @DisplayName("Menu total calories")
  public void shouldCalculateTotalMenuCalories() {
    int expectedTotalCalories = Math.toIntExact(EXPECTED_TOTAL_CALORIES);
    assertAll("Menu total calories",
        () -> assertThat(calculateTotalCalories(), is(expectedTotalCalories)),
        () -> assertThat(calculateTotalCalories2(), is(expectedTotalCalories)),
        () -> assertThat(calculateTotalCalories3(), is(expectedTotalCalories)),
        () -> assertThat(calculateTotalCalories4(), is(expectedTotalCalories)),
        () -> assertThat(calculateTotalCalories5(), is(expectedTotalCalories))
    );
  }

  @Test
  @DisplayName("Menu average calories")
  public void shouldCalculateMenuAverageCalories() {
    assertThat(calculateAverageCalories(), is(EXPECTED_MENU_AVERAGE_CALORIES));
  }

  @Test
  @DisplayName("Menu statistics")
  public void shouldCalculateMenuStatistics() {
    IntSummaryStatistics menuStatistics = calculateMenuStatistics();

    assertAll("Menu statistics",
        () -> assertThat(menuStatistics.getAverage(), is(EXPECTED_MENU_AVERAGE_CALORIES)),
        () -> assertThat(menuStatistics.getCount(), is(MENU_SIZE)),
        () -> assertThat(menuStatistics.getMax(), is(PORK.getCalories())),
        () -> assertThat(menuStatistics.getMin(), is(SEASON_FRUIT.getCalories())),
        () -> assertThat(menuStatistics.getSum(), is(EXPECTED_TOTAL_CALORIES))
    );
  }

  @Test
  @DisplayName("Short menu garbled")
  public void shouldGetShortMenuGarbled() {
    String expectedShortMenu = "porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon";

    assertAll("Short menu garbled",
        () -> assertThat(getShortMenuGarbled(), is(expectedShortMenu)),
        () -> assertThat(getShortMenuGarbledViaReducing1(), is(expectedShortMenu)),
        () -> assertThat(getShortMenuGarbledViaReducing3(), is(expectedShortMenu))
    );
  }

  @Test
  @DisplayName("Short menu")
  public void shouldGetShortMenu() {
    String expectedShortMenu = "pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon";

    assertThat(getShortMenu(), is(expectedShortMenu));
  }
}