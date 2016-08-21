package lambdasinaction.chap3;

import lambdasinaction.chap1.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static lambdasinaction.chap1.ExampleApples.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Sorting apples tests")
public class SortingTests {
  private List<Apple> inventory;
  private List<Apple> sortedApples =
      Arrays.asList(APPLE_GREEN_80, APPLE_RED_120, APPLE_GREEN_155, APPLE_RED_160);

  class AppleComparator implements Comparator<Apple> {
    public int compare(Apple a1, Apple a2) {
      return a1.getWeight().compareTo(a2.getWeight());
    }
  }

  @BeforeEach
  public void setUp() {
    inventory = Arrays.asList(
        APPLE_GREEN_80, APPLE_GREEN_155, APPLE_RED_120, APPLE_RED_160
    );
  }

  @Test
  @DisplayName("Sort apples with implemented comparator")
  public void shouldSortApplesWithImplementedComparator() {
    inventory.sort(new AppleComparator());
    assertThat(inventory, is(sortedApples));
  }

  @Test
  @DisplayName("Sort apples with anonymous comparator")
  public void shouldSortApplesWithAnonymousComparator() {
    inventory.sort(new Comparator<Apple>() {
      public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
      }
    });
    assertThat(inventory, is(sortedApples));
  }

  @Test
  @DisplayName("Sort apples with lambda")
  public void shouldSortApplesWithLambda() {
    inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
    assertThat(inventory, is(sortedApples));
  }

  @Test
  @DisplayName("Sort apples with comparing")
  public void shouldSortApplesWithComparing() {
    inventory.sort(comparing(a -> a.getWeight()));
    assertThat(inventory, is(sortedApples));
  }

  @Test
  @DisplayName("Sort apples with method reference")
  public void shouldSortApplesUsingMethodReference() {
    inventory.sort(comparing(Apple::getWeight));
    assertThat(inventory, is(sortedApples));
  }

  @Test
  @DisplayName("Sort apples into reverse order")
  public void shouldSortApplesIntoReverseOrder() {
    List<Apple> reverseSortedApples =
        Arrays.asList(APPLE_RED_160, APPLE_GREEN_155, APPLE_RED_120, APPLE_GREEN_80);

    inventory.sort(comparing(Apple::getWeight).reversed());
    assertThat(inventory, is(reverseSortedApples));
  }

  @Test
  @DisplayName("Sort apples into reverse order by colour then weight")
  public void shouldSortApplesIntoReverseOrderByColourThenWeight() {
    List<Apple> reverseSortedApplesByColourThenWeight =
        Arrays.asList(APPLE_RED_120, APPLE_RED_160, APPLE_GREEN_80, APPLE_GREEN_155);

    inventory.sort(comparing(Apple::getColour).reversed().thenComparing(Apple::getWeight));
    assertThat(inventory, is(reverseSortedApplesByColourThenWeight));
  }
}