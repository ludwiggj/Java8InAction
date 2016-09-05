package lambdasinaction.chap6;

import lambdasinaction.chap4.Dish;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap4.Restaurant.MENU;

public class GroupingDishes {

  enum CaloricLevel {DIET, NORMAL, FAT}

  private static final Function<Dish, CaloricLevel> dishCaloricLevel = dish -> {
    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
    else return CaloricLevel.FAT;
  };

  public static Map<Dish.Type, List<Dish>> groupDishesByType() {
    return MENU.stream().collect(groupingBy(Dish::getType));
  }

  public static Map<CaloricLevel, List<Dish>> groupDishesByCalorificLevel() {
    return MENU.stream().collect(groupingBy(dishCaloricLevel));
  }

  public static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndThenCalorificLevel() {
    return MENU.stream().collect(
        groupingBy(Dish::getType,
            groupingBy(dishCaloricLevel)
        )
    );
  }

  public static Map<Dish.Type, Long> countDishesInGroups() {
    return MENU.stream().collect(groupingBy(Dish::getType, Collectors.counting()));
  }

  public static Map<Dish.Type, Optional<Dish>> mostCalorificDishesByType() {
    return MENU.stream().collect(
        groupingBy(Dish::getType,
            Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
  }

  public static Map<Dish.Type, Optional<Dish>> mostCalorificDishesByType2() {
    return MENU.stream().collect(
        groupingBy(Dish::getType,
            reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
  }

  public static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
    return MENU.stream().collect(
        groupingBy(Dish::getType,
            collectingAndThen(
                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
                Optional::get)));
  }

  public static Map<Dish.Type, Integer> sumCaloriesByType() {
    return MENU.stream().collect(groupingBy(Dish::getType,
        summingInt(Dish::getCalories)));
  }

  public static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
    return MENU.stream().collect(
        groupingBy(Dish::getType, mapping(dishCaloricLevel, toSet()))
    );
  }

  public static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByTypeWithExplicitSet() {
    return MENU.stream().collect(
        groupingBy(Dish::getType, mapping(dishCaloricLevel, toCollection(HashSet::new)))
    );
  }
}