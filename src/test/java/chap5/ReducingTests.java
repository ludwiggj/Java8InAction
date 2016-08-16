package chap5;

import lambdasinaction.chap4.Dish;
import lambdasinaction.chap4.Restaurant;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap5.Reducing.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReducingTests {

  @Test
  public void shouldSumListOfNumbers() {
    List<Integer> numbers = Arrays.asList(3,4,5,1,2);

    assertThat(sumNumbersWithInitialValue(numbers), is(15));
  }

  @Test
  public void shouldSumListOfNumbers2() {
    List<Integer> numbers = Arrays.asList(3,4,5,1,2);

    assertThat(sumNumbersUsingMethodReferenceAndInitialValue(numbers), is(15));
  }

  @Test
  public void shouldReturnZeroWhenSumEmptyList() {
    List<Integer> numbers = Arrays.asList();

    assertThat(sumNumbersUsingMethodReferenceAndInitialValue(numbers), is(0));
  }

  @Test
  public void shouldSumListOfNumbersWhenListIsNotEmpty() {
    List<Integer> numbers = Arrays.asList(3,4,5,1,2);

    assertThat(sumNumbersUsingMethodReferenceButNoInitialValue(numbers).get(), is(15));
  }
  @Test
  public void shouldReturnEmptyWhenSumEmptyList() {
    List<Integer> numbers = Arrays.asList();

    assertThat(sumNumbersUsingMethodReferenceButNoInitialValue(numbers).isPresent(), is(false));
  }

  @Test
  public void shouldGetMax() {
    List<Integer> numbers = Arrays.asList(3,4,5,1,2);

    assertThat(getMaxUsingMethodReferenceAndInitialValue(numbers), is(5));
  }

  @Test
  public void getMaxWithInitialValueShouldReturnIncorrectResultIfAllNumbersLessThanZero() {
    List<Integer> numbers = Arrays.asList(-3,-4,-5,-1,-2);

    assertThat(getMaxUsingMethodReferenceAndInitialValue(numbers), is(0));
  }

  @Test
  public void getMaxWithInitialValueShouldReturnZeroWhenListIsEmpty() {
    List<Integer> numbers = Arrays.asList();

    assertThat(getMaxUsingMethodReferenceAndInitialValue(numbers), is(0));
  }

  @Test
  public void shouldGetMax2() {
    List<Integer> numbers = Arrays.asList(3,4,5,1,2);

    assertThat(getMaxUsingMethodReferenceButNoInitialValue(numbers).get(), is(5));
  }

  @Test
  public void shouldGetMaxWhenAllNumbersLessThanZero() {
    List<Integer> numbers = Arrays.asList(-3,-4,-5,-1,-2);

    assertThat(getMaxUsingMethodReferenceButNoInitialValue(numbers).get(), is(-1));
  }

  @Test
  public void shouldReturnEmptyWhenGetMaxWhenListIsEmpty() {
    List<Integer> numbers = Arrays.asList();

    assertThat(getMaxUsingMethodReferenceButNoInitialValue(numbers).isPresent(), is(false));
  }

  // Q 5.3; How would you count the number of dishes in a stream using the map and reduce methods?
  @Test
  public void shouldCountNumberOfDishesInMenu() {
    assertThat(countDishes(Restaurant.menu), is(9));
  }

  @Test
  public void shouldReturnZeroDishesForEmptyMenu() {
    List<Dish> emptyMenu = Arrays.asList();

    assertThat(countDishes(emptyMenu), is(0));
  }
}