package lambdasinaction.chap2;

import lambdasinaction.chap1.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static lambdasinaction.chap1.ExampleApples.APPLE_GREEN_155;
import static lambdasinaction.chap1.ExampleApples.APPLE_GREEN_80;
import static lambdasinaction.chap1.ExampleApples.APPLE_RED_120;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SortingApplesTests {

  private List<Apple> inventory;
  private List<Apple> expectedApplesSortedByWeight =
      Arrays.asList(APPLE_GREEN_80, APPLE_RED_120, APPLE_GREEN_155);

  @BeforeEach
  public void setUp() {
    inventory = Arrays.asList(APPLE_GREEN_80, APPLE_GREEN_155, APPLE_RED_120);
  }

  @Test
  @DisplayName("Sort apples by weight via anonymous class")
  public void shouldSortApplesVerbose() {
    inventory.sort(new Comparator<Apple>() {
      public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
      }
    });

    assertThat(inventory, is(expectedApplesSortedByWeight));
  }

  @Test
  @DisplayName("Sort apples by weight via lambda")
  public void shouldSortApplesLambda() {
    inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

    assertThat(inventory, is(expectedApplesSortedByWeight));
  }
}