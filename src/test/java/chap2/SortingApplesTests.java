package chap2;

import lambdasinaction.chap1.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SortingApplesTests {

  private List<Apple> inventory;
  private List<Apple> expectedSortedApples =
      Arrays.asList(new Apple(80, "green"), new Apple(120, "red"), new Apple(155, "green"));

  @Before
  public void setUp() {
    inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
    ;
  }

  @Test
  public void shouldSortApplesVerbose() {
    inventory.sort(new Comparator<Apple>() {
      public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
      }
    });

    assertThat(inventory, is(expectedSortedApples));
  }

  @Test
  public void shouldSortApplesLambda() {
    inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

    assertThat(inventory, is(expectedSortedApples));
  }
}