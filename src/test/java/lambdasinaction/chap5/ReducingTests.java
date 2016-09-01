package lambdasinaction.chap5;

import lambdasinaction.chap4.Restaurant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap5.Reducing.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ReducingTests {

  public static final List<Integer> INTEGER_LIST = Arrays.asList(3, 4, 5, 1, 2);
  public static final List<Integer> EMPTY_INTEGER_LIST = Arrays.asList();
  public static final List<Integer> NEGATIVE_INTEGER_LIST = Arrays.asList(-3, -4, -5, -1, -2);

  @Test
  @DisplayName("Summing a list of numbers with initial value")
  public void shouldSumListOfNumbersWithInitialValue() {

    assertAll("Sum list of numbers",
        () -> assertThat(sumNumbersWithInitialValue(INTEGER_LIST), is(15)),
        () -> assertThat(sumNumbersUsingMethodReferenceAndInitialValue(INTEGER_LIST), is(15)),
        () -> assertThat(sumNumbersUsingMethodReferenceAndInitialValue(EMPTY_INTEGER_LIST), is(0))
    );
  }

  @Test
  @DisplayName("Summing a list of numbers without initial value")
  public void shouldSumListOfNumbersWithoutInitialValue() {
    assertAll("Sum list of numbers",
        () -> assertThat(sumNumbersUsingMethodReferenceButNoInitialValue(INTEGER_LIST).get(), is(15)),
        () -> assertThat(sumNumbersUsingMethodReferenceButNoInitialValue(EMPTY_INTEGER_LIST).isPresent(), is(false))
    );
  }

  @Test
  @DisplayName("Max of list of numbers with initial value")
  public void shouldGetMax() {
    assertAll("Get max of list of numbers",
        () -> assertThat(getMaxUsingMethodReferenceAndInitialValue(INTEGER_LIST), is(5)))
    ;
  }

  @Test
  @DisplayName("Max of list of numbers with initial value is incorrect when all numbers are negative")
  public void getMaxWithInitialValueShouldReturnIncorrectResultIfAllNumbersLessThanZero() {
    assertThat(getMaxUsingMethodReferenceAndInitialValue(NEGATIVE_INTEGER_LIST), is(0));
  }

  @Test
  @DisplayName("Max of list of numbers with initial value is incorrect when list is empty")
  public void getMaxWithInitialValueShouldReturnZeroWhenListIsEmpty() {
    assertThat(getMaxUsingMethodReferenceAndInitialValue(EMPTY_INTEGER_LIST), is(0));
  }

  @Test
  @DisplayName("Max of list of numbers without initial value")
  public void shouldGetMax2() {
    assertThat(getMaxUsingMethodReferenceButNoInitialValue(INTEGER_LIST).get(), is(5));
  }

  @Test
  @DisplayName("Max of list of numbers without initial value is correct when all numbers are negative")
  public void shouldGetMaxWhenAllNumbersLessThanZero() {
    assertThat(getMaxUsingMethodReferenceButNoInitialValue(NEGATIVE_INTEGER_LIST).get(), is(-1));
  }

  @Test
  @DisplayName("Max of list of numbers without initial value is correct when list is empty")
  public void shouldReturnEmptyWhenGetMaxWhenListIsEmpty() {
    assertThat(getMaxUsingMethodReferenceButNoInitialValue(EMPTY_INTEGER_LIST).isPresent(), is(false));
  }

  // Q 5.3; How would you count the number of dishes in a stream using the map and reduce methods?
  @Test
  @DisplayName("Counting dishes")
  public void shouldCountNumberOfDishesInMenu() {
    assertAll("Count dishes",
        () -> assertThat(countDishes(Restaurant.MENU), is(9)),
        () -> assertThat(countDishes(Arrays.asList()), is(0))
    );
  }

  @Test
  @DisplayName("Sum MENU calories")
  public void shouldSumMenuCalories() {
    assertThat(sumMenuCalories(Restaurant.MENU), is(4300));
  }
}