package lambdasinaction.chap4;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Streaming {

  public static List<String> getSortedLowCaloricDishesNamesInJava7(List<Dish> dishes) {
    List<Dish> lowCaloricDishes = new ArrayList<>();
    for (Dish d : dishes) {
      if (d.getCalories() < 400) {
        lowCaloricDishes.add(d);
      }
    }
    List<String> lowCaloricDishesName = new ArrayList<>();
    Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
      public int compare(Dish d1, Dish d2) {
        return Integer.compare(d1.getCalories(), d2.getCalories());
      }
    });
    for (Dish d : lowCaloricDishes) {
      lowCaloricDishesName.add(d.getName());
    }
    return lowCaloricDishesName;
  }

  public static List<String> getSortedLowCaloricDishesNamesInJava8(List<Dish> dishes) {
    return dishes.stream()
        .filter(d -> d.getCalories() < 400)
        .sorted(comparing(Dish::getCalories))
        .map(Dish::getName)
        .collect(toList());
  }

  public static List<String> getThreeHighCaloricDishNames(List<Dish> dishes) {
    return dishes.stream()
        .filter(d -> {
          System.out.println("filtering " + d.getName());
          return d.getCalories() > 300;
        })
        .map(d -> {
          System.out.println("mapping " + d.getName());
          return d.getName();
        })
        .limit(3)
        .collect(toList());
  }

  public static Map<Dish.Type, List<Dish>> getDishesByType(List<Dish> dishes) {
    Map<Dish.Type, List<Dish>> dishesByType =
        dishes.stream().collect(groupingBy(Dish::getType));

    return dishesByType;
  }
}