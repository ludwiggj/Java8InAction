package chap5;

import org.junit.Test;

import static lambdasinaction.chap5.Finding.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FindingTests {

  @Test
  public void shouldFilterVeggieDishes() {
    assertThat(isVegetarianFriendlyMenu(), is(true));
  }

  @Test
  public void shouldDetermineWhetherMenuIsHealthy() {
    assertThat(isHealthyMenu(), is(true));
  }

  @Test
  public void shouldDetermineWhetherMenuIsHealthy2() {
    assertThat(isHealthyMenu2(), is(true));
  }

  @Test
  public void shouldFindVeggieDish() {
    assertThat(findVegetarianDish().isPresent(), is(true));
  }

  @Test
  public void shouldFindFirstSquareDivisibleByThree() throws Exception {
    assertThat(findFirstSquareDivisibleByThree().get(), is(9));
  }
}
