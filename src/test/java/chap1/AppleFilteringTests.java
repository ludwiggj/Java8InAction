package chap1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import lambdasinaction.chap1.Apple;
import lambdasinaction.chap1.FilteringApples;
import static lambdasinaction.chap1.Apple.GREEN;
import static lambdasinaction.chap1.Apple.HEAVY_WEIGHT;
import static lambdasinaction.chap1.Apple.RED;
import static lambdasinaction.chap1.FilteringApples.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AppleFilteringTests {
  public static final Apple APPLE_GREEN_80 = new Apple(80, GREEN);
  public static final Apple APPLE_GREEN_155 = new Apple(155, GREEN);
  public static final Apple APPLE_RED_120 = new Apple(120, RED);

  private List<Apple> inventory;

  @BeforeEach
  public void setup() {
    inventory = Arrays.asList(APPLE_GREEN_80, APPLE_GREEN_155, APPLE_RED_120);
  }

  @Test
  @DisplayName("Should select green apples")
  public void greenAppleTest() {
    // Green apples
    List<Apple> greenApples = Arrays.asList(APPLE_GREEN_80, APPLE_GREEN_155);

    // In a grouped assertion all assertions are executed, and any failures will be reported together.
    assertAll("green apples",
        () -> assertThat(filterApples(inventory, FilteringApples::isGreenApple), is(greenApples)),
        () -> assertThat(filterApples(inventory, (Apple a) -> GREEN.equals(a.getColour())), is(greenApples)),
        () -> assertThat(filterGreenApples(inventory), is(greenApples))
    );
  }

  @Test
  @DisplayName("Should select heavy apples")
  public void heavyAppleTest() {
    List<Apple> heavyApples = Arrays.asList(APPLE_GREEN_155);

    assertAll("heavy apples",
        () -> assertThat(filterApples(inventory, FilteringApples::isHeavyApple), is(heavyApples)),
        () -> assertThat(filterApples(inventory, (Apple a) -> a.getWeight() > HEAVY_WEIGHT), is(heavyApples)),
        () -> assertThat(filterHeavyApples(inventory), is(heavyApples))
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