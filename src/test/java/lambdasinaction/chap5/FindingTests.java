package lambdasinaction.chap5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lambdasinaction.chap5.Finding.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FindingTests {

  @Test
  @DisplayName("Vegetarian friendly MENU (anyMatch)")
  public void shouldFilterVeggieDishes() {
    assertThat(isVegetarianFriendlyMenu(), is(true));
  }

  @Test
  @DisplayName("Healthy MENU (allMatch)")
  public void shouldDetermineWhetherMenuIsHealthy() {
    assertThat(isHealthyMenu(), is(true));
  }

  @Test
  @DisplayName("Healthy MENU (noneMatch)")
  public void shouldDetermineWhetherMenuIsHealthy2() {
    assertThat(isHealthyMenu2(), is(true));
  }

  @Test
  @DisplayName("Find veggie dish")
  public void shouldFindVeggieDish() {
    assertThat(findVegetarianDish().isPresent(), is(true));
  }

  @Test
  @DisplayName("Find first square divisible by three(")
  public void shouldFindFirstSquareDivisibleByThree() throws Exception {
    assertThat(findFirstSquareDivisibleByThree().get(), is(9));
  }
}
