package lambdasinaction.chap5;

import lambdasinaction.chap4.*;

import java.util.*;

import static java.util.stream.Collectors.toList;

import static lambdasinaction.chap4.Restaurant.menu;

public class Filtering {

  public static List<Dish> getVegetarianDishes() {
    return menu.stream()
        .filter(Dish::isVegetarian)
        .collect(toList());
  }

  public static List<Integer> filterEvenNumbers() {
    List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
    return numbers.stream()
        .filter(i -> i % 2 == 0)
        .distinct()
        .collect(toList());
  }

  public static List<Dish> getFirstThreeDishesOver300Calories() {
    return menu.stream()
        .filter(d -> d.getCalories() > 300)
        .limit(3)
        .collect(toList());
  }

  public static List<Dish> getAllButFirstTwoDishesOver300Calories() {
    return menu.stream()
        .filter(d -> d.getCalories() > 300)
        .skip(2)
        .collect(toList());
  }

  public static List<Dish> getFirstTwoMeatDishes() {
    return menu.stream()
        .filter(d -> d.getType() == Dish.Type.MEAT)
        .limit(2)
        .collect(toList());
  }
}