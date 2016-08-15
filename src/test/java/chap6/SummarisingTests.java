package chap6;

import lambdasinaction.chap6.Dish;
import org.junit.Test;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.NoSuchElementException;

import static lambdasinaction.chap6.Dish.PORK;
import static lambdasinaction.chap6.Dish.SEASON_FRUIT;
import static lambdasinaction.chap6.Dish.menu;
import static lambdasinaction.chap6.Summarising.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SummarisingTests {

  private static final long EXPECTED_TOTAL_CALORIES = 4300;
  private static final long MENU_SIZE = menu.size();
  private static final double EXPECTED_MENU_AVERAGE_CALORIES = (double) EXPECTED_TOTAL_CALORIES / MENU_SIZE;

  @Test
  public void shouldCountNumberOfDishes() {
    assertThat(howManyDishes(), is((long) Dish.menu.size()));
  }

  @Test
  public void shouldCountNumberOfDishesDirect() {
    assertThat(howManyDishesDirect(), is((long) Dish.menu.size()));
  }

  @Test
  public void shouldFindMostCalorificDish() {
    assertThat(findMostCalorificDish(menu), is(Dish.PORK));
  }

  @Test
  public void shouldFindMostCalorificDish2() {
    assertThat(findMostCalorificDish2(), is(Dish.PORK));
  }

  @Test(expected=NoSuchElementException.class)
  public void shouldFindMostCalorificDishFromEmptyMenu() {
    findMostCalorificDish(new ArrayList<>());
  }

  @Test
  public void shouldFindMostCalorificDishUsingComparator() {
    assertThat(findMostCalorificDishUsingComparator(), is(Dish.PORK));
  }

  @Test
  public void shouldFindMostCalorificDishUsingComparator2() {
    assertThat(findMostCalorificDishUsingComparator2(), is(Dish.PORK));
  }

  @Test
  public void shouldCalculateTotalMenuCalories() {
    assertThat(calculateTotalCalories(), is((int) EXPECTED_TOTAL_CALORIES));
  }

  @Test
  public void shouldCalculateMenuAverageCalories() {
    assertThat(calculateAverageCalories(), is(EXPECTED_MENU_AVERAGE_CALORIES));
  }

  @Test
  public void shouldCalculateMenuStatistics() {
    IntSummaryStatistics menuStatistics = calculateMenuStatistics();

    assertThat(menuStatistics.getAverage(), is(EXPECTED_MENU_AVERAGE_CALORIES));
    assertThat(menuStatistics.getCount(), is(MENU_SIZE));
    assertThat(menuStatistics.getMax(), is(PORK.getCalories()));
    assertThat(menuStatistics.getMin(), is(SEASON_FRUIT.getCalories()));
    assertThat(menuStatistics.getSum(), is(EXPECTED_TOTAL_CALORIES));
  }
}