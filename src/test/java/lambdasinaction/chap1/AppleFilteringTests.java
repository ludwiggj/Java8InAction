package lambdasinaction.chap1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap1.ExampleApples.*;
import static lambdasinaction.chap1.Apple.GREEN;
import static lambdasinaction.chap1.Apple.HEAVY_WEIGHT;
import static lambdasinaction.chap1.FilteringApples.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AppleFilteringTests {

  private List<Apple> inventory;

  @BeforeEach
  public void setup() {
    inventory = Arrays.asList(APPLE_GREEN_80, APPLE_GREEN_155, APPLE_RED_120);
  }

  @Test
  @DisplayName("Should select green apples")
  public void greenAppleTest() {
    // In a grouped assertion all assertions are executed, and any failures will be reported together.
    assertAll("green apples",
        () -> assertThat(filterApples(inventory, FilteringApples::isGreenApple), is(GREEN_APPLES)),
        () -> assertThat(filterApples(inventory, (Apple a) -> GREEN.equals(a.getColour())), is(GREEN_APPLES)),
        () -> assertThat(filterGreenApples(inventory), is(GREEN_APPLES))
    );
  }

  @Test
  @DisplayName("Should select heavy apples")
  public void heavyAppleTest() {
    assertAll("heavy apples",
        () -> assertThat(filterApples(inventory, FilteringApples::isHeavyApple), is(HEAVY_APPLES)),
        () -> assertThat(filterApples(inventory, (Apple a) -> a.getWeight() > HEAVY_WEIGHT), is(HEAVY_APPLES)),
        () -> assertThat(filterHeavyApples(inventory), is(HEAVY_APPLES))
    );
  }

  @Test
  @DisplayName("Should return empty list if nothing matches")
  public void weirdFilterShouldReturnEmptyList() {
    List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 8 ||
        "brown".equals(a.getColour()));

    assertThat(weirdApples, is(empty()));
  }
}