package lambdasinaction.chap6;

import lambdasinaction.chap4.Dish;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import static lambdasinaction.chap4.Restaurant.MENU;

public class ReducingAndSummarising {

  public static long howManyDishes() {
    return MENU.stream().collect(Collectors.counting());
  }

  public static long howManyDishesDirect() {
    return MENU.stream().count();
  }

  public static Dish findMostCalorificDishViaMaxBy() {
    Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
    return MENU.stream().collect(Collectors.maxBy(dishCaloriesComparator)).get();
  }

  // Generalised version of max, using reducing
  public static Dish findMostCalorificDishViaReducing(List<Dish> menu) {
    return menu.stream().collect(
        Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
    ).get();
  }

  public static Dish findMostCalorificViaReducingExpanded() {
    BinaryOperator<Dish> moreCalorificOf = ((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
    return MENU.stream().collect(Collectors.reducing(moreCalorificOf)).get();
  }

  public static int calculateTotalCalories() {
    return MENU.stream().collect(Collectors.summingInt(Dish::getCalories));
  }

  public static int calculateTotalCalories2() {
    return MENU.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
  }

  public static int calculateTotalCalories3() {
    return MENU.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
  }

  public static int calculateTotalCalories4() {
    return MENU.stream().map(Dish::getCalories).reduce(Integer::sum).get();
  }

  public static int calculateTotalCalories5() {
    return MENU.stream().mapToInt(Dish::getCalories).sum();
  }

  public static Double calculateAverageCalories() {
    return MENU.stream().collect(Collectors.averagingInt(Dish::getCalories));
  }

  public static IntSummaryStatistics calculateMenuStatistics() {
    return MENU.stream().collect(Collectors.summarizingInt(Dish::getCalories));
  }

  public static String getShortMenuGarbled() {
    return MENU.stream().map(Dish::getName).collect(Collectors.joining());
  }

  // Quiz 6.1, answer 1
  public static String getShortMenuGarbledViaReducing1() {
    return MENU.stream().map(Dish::getName).collect(Collectors.reducing((s1, s2) -> s1 + s2)).get();
  }

  // Quiz 6.1, answer 2
  // public static String getShortMenuGarbledViaReducing2() {
  // This doesn't compile, reducing expects a BinaryOperator<T> which extends BiFunction<T, T, T>
  // i.e. lambda has to return same type as the operands (compare to answer 1 above)
  // return MENU.stream().collect(Collectors.reducing((d1, d2) -> d1.getName() + d2.getName())).get();
  // }

  // Quiz 6.1, answer 3
  // This answer uses a generalization of {@link #reducing(Object, BinaryOperator)} which allows a transformation
  // of the elements before reduction.
  public static String getShortMenuGarbledViaReducing3() {
    return MENU.stream().collect(Collectors.reducing("", Dish::getName, (s1, s2) -> s1 + s2));
  }

  public static String getShortMenu() {
    return MENU.stream().map(Dish::getName).collect(Collectors.joining(", "));
  }
}