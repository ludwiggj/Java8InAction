package lambdasinaction.chap2;

import lambdasinaction.chap1.Apple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap1.ExampleApples.*;
import static lambdasinaction.chap1.Apple.GREEN;
import static lambdasinaction.chap1.Apple.HEAVY_WEIGHT;
import static lambdasinaction.chap1.Apple.RED;
import static lambdasinaction.chap2.FilteringApples.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Filtering")
public class AppleFilteringTests {

  public class AppleColourPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
      return GREEN.equals(apple.getColour());
    }
  }

  public class AppleWeightPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
      return apple.getWeight() > HEAVY_WEIGHT;
    }
  }

  public class AppleRedAndHeavyPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
      return RED.equals(apple.getColour())
          && apple.getWeight() > HEAVY_WEIGHT;
    }
  }

  @Nested
  @DisplayName("evolving apples")
  class EvolvingApples {
    private List<Apple> inventory;

    @BeforeEach
    public void setup() {
      inventory = Arrays.asList(APPLE_GREEN_80, APPLE_GREEN_155, APPLE_RED_120, APPLE_RED_160);
    }

    @Test
    @DisplayName("initial version")
    public void take1Test() {
      assertThat(filterGreenApples(inventory), is(GREEN_APPLES));
    }

    @Test
    @DisplayName("parameterise by colour")
    public void take2ParameteriseByColourTest() {
      assertAll("apples by colour",
          () -> assertThat(filterApplesByColour(inventory, GREEN), is(GREEN_APPLES)),
          () -> assertThat(filterApplesByColour(inventory, RED), is(RED_APPLES))
      );
    }

    @Test
    @DisplayName("parameterise by weight")
    public void take2ParameteriseByWeightTest() {
      assertThat(filterApplesByWeight(inventory, HEAVY_WEIGHT), is(HEAVY_APPLES));
    }

    @Test
    @DisplayName("predicate for colour")
    public void take4ColourPredicate() {
      assertThat(filter(inventory, new AppleColourPredicate()), is(GREEN_APPLES));
    }

    @Test
    @DisplayName("predicate for weight")
    public void take4WeightPredicate() {
      assertThat(filter(inventory, new AppleWeightPredicate()), is(HEAVY_APPLES));
    }

    @Test
    @DisplayName("predicate for colour and weight")
    public void take4AppleRedAndHeavyPredicate() {
      assertThat(filter(inventory, new AppleRedAndHeavyPredicate()), is(Arrays.asList(APPLE_RED_160)));
    }

    @Test
    @DisplayName("predicate for colour using anonymous class")
    public void take5AnonymousClass() {
      List<Apple> redApples = filter(inventory, new ApplePredicate() {
        public boolean test(Apple a) {
          return RED.equals(a.getColour());
        }
      });

      assertThat(redApples, is(RED_APPLES));
    }

    @Test
    @DisplayName("using lambda expressions")
    public void take6LambdaExpressions() {
      assertAll("lambda expressions",
          () -> assertThat(filter(inventory, (Apple a) -> RED.equals(a.getColour())), is(RED_APPLES)),
          () -> assertThat(filter(inventory, a -> RED.equals(a.getColour())), is(RED_APPLES))
      );
    }

    @Test
    @DisplayName("over abstract list")
    public void take7AbstractOverListTypeRedApples() {
      assertThat(filterT(inventory, a -> RED.equals(a.getColour())), is(RED_APPLES));
    }
  }

  @Test
  @DisplayName("over abstract list")
  public void take7AbstractOverListTypeEvenNumbers() {
    List<Integer> zeroToTen = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<Integer> expectedEvenNumbers = Arrays.asList(0, 2, 4, 6, 8, 10);

    assertThat(filterT(zeroToTen, i -> i % 2 == 0), is(expectedEvenNumbers));
  }
}