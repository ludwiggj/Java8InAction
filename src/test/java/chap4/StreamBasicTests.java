package chap4;

import lambdasinaction.chap4.Restaurant;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap4.StreamBasic.getLowCaloricDishesNamesInJava7;
import static lambdasinaction.chap4.StreamBasic.getLowCaloricDishesNamesInJava8;
import static lambdasinaction.chap4.StreamBasic.getThreeHighCaloricDishNames;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StreamBasicTests {

  private static List<String> expectedLowCaloricDishNames =
      Arrays.asList(Restaurant.SEASON_FRUIT.getName(), Restaurant.RICE.getName());

  private static List<String> expectedHighCaloricDishNames =
      Arrays.asList(Restaurant.PORK.getName(), Restaurant.BEEF.getName(), Restaurant.CHICKEN.getName());

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
