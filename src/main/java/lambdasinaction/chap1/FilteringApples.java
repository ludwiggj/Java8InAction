package lambdasinaction.chap1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static lambdasinaction.chap1.Apple.GREEN;

public class FilteringApples {

  public static List<Apple> filterGreenApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (GREEN.equals(apple.getColour())) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filterHeavyApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (isHeavyApple(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  public static boolean isGreenApple(Apple apple) {
    return GREEN.equals(apple.getColour());
  }

  public static boolean isHeavyApple(Apple apple) {
    return apple.isHeavy();
  }

  public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }
}