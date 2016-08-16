package chap2;

import lambdasinaction.chap1.Apple;
import lambdasinaction.chap2.AppleColourPredicate;

import lambdasinaction.chap2.ApplePredicate;
import lambdasinaction.chap2.AppleRedAndHeavyPredicate;
import lambdasinaction.chap2.AppleWeightPredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap2.FilteringApples.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

public class AppleFilteringTests {

  private List<Apple> inventory;
  private List<Apple> expectedGreenApples = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"));
  private List<Apple> expectedRedApples = Arrays.asList(new Apple(120, "red"));
  private List<Apple> expectedHeavyApples = Arrays.asList(new Apple(155, "green"));

  @BeforeEach
  public void setup() {
    inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
  }

  @Test
  public void take1Test() {
    assertThat(filterGreenApples(inventory), is(expectedGreenApples));
  }

  @Test
  public void take2ParameteriseByColourTest() {
    assertThat(filterApplesByColour(inventory, "green"), is(expectedGreenApples));
    assertThat(filterApplesByColour(inventory, "red"), is(expectedRedApples));
  }

  @Test
  public void take2ParameteriseByWeightTest() {
    assertThat(filterApplesByWeight(inventory, 150), is(expectedHeavyApples));
  }

  @Test
  public void take4ColourPredicate() {
    assertThat(filter(inventory, new AppleColourPredicate()), is(expectedGreenApples));
  }

  @Test
  public void take4WeightPredicate() {
    assertThat(filter(inventory, new AppleWeightPredicate()), is(expectedHeavyApples));
  }

  @Test
  public void take4AppleRedAndHeavyPredicate() {
    assertThat(filter(inventory, new AppleRedAndHeavyPredicate()), is(empty()));
  }

  @Test
  public void take5AnonymousClass() {
    List<Apple> redApples = filter(inventory, new ApplePredicate() {
      public boolean test(Apple a) {
        return "red".equals(a.getColour());
      }
    });

    assertThat(redApples, is(expectedRedApples));
  }

  @Test
  public void take6LambdaExpressions() {
    assertThat(filter(inventory, (Apple a) -> "red".equals(a.getColour())), is(expectedRedApples));
    assertThat(filter(inventory, a -> "red".equals(a.getColour())), is(expectedRedApples));
  }

  @Test
  public void take7AbstractOverListTypeRedApples() {
    assertThat(filterT(inventory, a -> "red".equals(a.getColour())), is(expectedRedApples));
  }

  @Test
  public void take7AbstractOverListTypeEvenNumbers() {
    List<Integer> zeroToTen = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<Integer> expectedEvenNumbers = Arrays.asList(0, 2, 4, 6, 8, 10);

    assertThat(filterT(zeroToTen, i -> i % 2 == 0), is(expectedEvenNumbers));
  }
}