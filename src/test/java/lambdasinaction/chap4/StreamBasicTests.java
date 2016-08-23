package lambdasinaction.chap4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap4.Restaurant.*;
import static lambdasinaction.chap4.StreamBasic.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StreamBasicTests {

  private static List<String> expectedLowCaloricDishNames =
      Arrays.asList(SEASON_FRUIT, RICE)
          .stream()
          .map(Dish::getName)
          .collect(toList());

  private static List<String> expectedHighCaloricDishNames =
      Arrays.asList(PORK, BEEF, CHICKEN)
                .stream()
                .map(Dish::getName)
                .collect(toList());

  @Test
  public void shouldGetLowCaloricDishesNamesInJava7() throws Exception {
    assertThat(getLowCaloricDishesNamesInJava7(Restaurant.menu), is(expectedLowCaloricDishNames));
  }

  @Test
  public void shouldGetLowCaloricDishesNamesInJava8() throws Exception {
    assertThat(getLowCaloricDishesNamesInJava8(Restaurant.menu), is(expectedLowCaloricDishNames));
  }

  @Test
  public void shouldGetThreeHighCaloricDishNames() throws Exception {
    assertThat(getThreeHighCaloricDishNames(Restaurant.menu), is(expectedHighCaloricDishNames));
  }
}
