package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static lambdasinaction.chap4.Restaurant.MENU;

public class Finding {

  public static boolean isVegetarianFriendlyMenu() {
    return MENU.stream().anyMatch(Dish::isVegetarian);
  }

  public static boolean isHealthyMenu() {
    return MENU.stream().allMatch(d -> d.getCalories() < 1000);
  }

  public static boolean isHealthyMenu2() {
    return MENU.stream().noneMatch(d -> d.getCalories() >= 1000);
  }

  public static Optional<Dish> findVegetarianDish() {
    return MENU.stream().filter(Dish::isVegetarian).findAny();
  }

  public static Optional<Integer> findFirstSquareDivisibleByThree() {
    List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
    return someNumbers.stream()
        .map(x -> x * x)
        .filter(x -> x % 3 == 0)
        .findFirst();
  }
}
