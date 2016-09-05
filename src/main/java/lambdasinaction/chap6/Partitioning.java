package lambdasinaction.chap6;

import lambdasinaction.chap4.Dish;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static lambdasinaction.chap4.Restaurant.MENU;

public class Partitioning {

  public static Map<Boolean, List<Dish>> partitionByVegetarian() {
    return MENU.stream().collect(partitioningBy(Dish::isVegetarian));
  }

  public static Map<Boolean, Map<Dish.Type, List<Dish>>> vegAndNonVegDishesByType() {
    return MENU.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
  }

  public static Map<Boolean, Dish> mostCaloricPartitionedByVegetarian() {
    return MENU.stream().collect(
        partitioningBy(Dish::isVegetarian,
            collectingAndThen(
                maxBy(comparingInt(Dish::getCalories)),
                Optional::get)));
  }

  public static Map<Boolean, Map<Boolean, List<Dish>>> dishesOf500CaloriesOrMoreByVegetarian() {
    return MENU.stream().collect(partitioningBy(Dish::isVegetarian,
        partitioningBy(d -> d.getCalories() > 500)));
  }

  public static Map<Boolean, Long> countNumberOfVeggyAndNonVeggyDishes() {
    return MENU.stream().collect(partitioningBy(Dish::isVegetarian, counting()));
  }
}

