package chap1;

import lambdasinaction.chap1.Apple;
import lambdasinaction.chap1.FilteringApples;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap1.FilteringApples.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.MatcherAssert.assertThat;

public class AppleFilteringTests {

  private List<Apple> inventory;

  @Before
  public void setup() {
    inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
  }

  @Test
  public void greenAppleTest() {
    // Green apples
    List<Apple> expectedGreenApples = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"));

    assertThat(filterApples(inventory, FilteringApples::isGreenApple), is(expectedGreenApples));

    assertThat(filterApples(inventory, (Apple a) -> "green".equals(a.getColor())), is(expectedGreenApples));

    assertThat(filterGreenApples(inventory), is(expectedGreenApples));
  }

  @Test
  public void heavyAppleTest() {
    List<Apple> expectedHeavyApples = Arrays.asList(new Apple(155, "green"));

    assertThat(filterApples(inventory, FilteringApples::isHeavyApple), is(expectedHeavyApples));

    assertThat(filterApples(inventory, (Apple a) -> a.getWeight() > 150), is(expectedHeavyApples));

    assertThat(filterHeavyApples(inventory), is(expectedHeavyApples));
  }

  @Test
  public void weirdFilterShouldReturnEmptyList() throws Exception {
    List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 8 ||
        "brown".equals(a.getColor()));

    assertThat(weirdApples, is(empty()));
  }
}