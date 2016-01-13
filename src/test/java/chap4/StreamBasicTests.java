package chap4;

import lambdasinaction.chap4.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap4.StreamBasic.getLowCaloricDishesNamesInJava7;
import static lambdasinaction.chap4.StreamBasic.getLowCaloricDishesNamesInJava8;
import static lambdasinaction.chap4.StreamBasic.getThreeHighCaloricDishNames;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StreamBasicTests {

  private static List<String> expectedLowCaloricDishNames = Arrays.asList("season fruit", "rice");
  private static List<String> expectedHighCaloricDishNames = Arrays.asList("pork", "beef", "chicken");

  @Test
  public void shouldGetLowCaloricDishesNamesInJava7() throws Exception {
    assertThat(getLowCaloricDishesNamesInJava7(Dish.menu), is(expectedLowCaloricDishNames));
  }

  @Test
  public void shouldGetLowCaloricDishesNamesInJava8() throws Exception {
    assertThat(getLowCaloricDishesNamesInJava8(Dish.menu), is(expectedLowCaloricDishNames));
  }

  @Test
  public void shouldGetThreeHighCaloricDishNames() throws Exception {
    assertThat(getThreeHighCaloricDishNames(Dish.menu), is(expectedHighCaloricDishNames));
  }
}
