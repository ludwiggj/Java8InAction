package lambdasinaction.chap6;

import lambdasinaction.chap4.Dish;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static lambdasinaction.chap4.Dish.*;
import static lambdasinaction.chap4.Restaurant.MENU;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ToListCollectorTests {

  @Test
  @DisplayName("Collect non-empty stream")
  public void shouldCollectNonEmptyStream() {
    Stream<Integer> numbers = IntStream.rangeClosed(1, 5).boxed();
    Stream<String> strings = Stream.of("The", "cat", "sat", "on", "the", "mat");

    assertAll("Collect non-empty stream",
        () -> assertThat(numbers.collect(new ToListCollector<>()), is(asList(1, 2, 3, 4, 5))),
        () -> assertThat(strings.collect(new ToListCollector<>()), is(
            asList("The", "cat", "sat", "on", "the", "mat")
        ))
    );
  }

  @Test
  @DisplayName("Collect empty stream")
  public void shouldCollectStreamOfIntegers() {
    assertAll("Collect empty stream",
        () -> assertThat(Stream.<Integer>empty().collect(new ToListCollector<>()), is(asList())),
        () -> assertThat(Stream.<String>empty().collect(new ToListCollector<>()), is(asList()))
    );
  }

  private static List<Dish> collectDishesViaCustomCollector() {
    return MENU.stream().collect(ArrayList::new, List::add, List::addAll);
  }

  @Test
  @DisplayName("Collect restaurant menu")
  public void shouldCollectRestaurantMenu() {
    List<Dish> dishList = asList(PORK, BEEF, CHICKEN, FRENCH_FRIES, RICE, SEASON_FRUIT, PIZZA, PRAWNS, SALMON);

    assertAll("Collect restaurant menu",
        () -> assertThat(MENU.stream().collect(new ToListCollector<>()), is(dishList)),
        () -> assertThat(collectDishesViaCustomCollector(), is(dishList))
    );
  }
}