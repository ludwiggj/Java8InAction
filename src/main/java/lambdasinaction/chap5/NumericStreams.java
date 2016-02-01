package lambdasinaction.chap5;

import lambdasinaction.chap4.*;

import java.util.stream.*;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static lambdasinaction.chap4.Dish.Type.FISH;
import static lambdasinaction.chap4.Restaurant.MYSTERY_FISH;
import static lambdasinaction.chap4.Restaurant.NOT_VEGETARIAN;
import static lambdasinaction.chap4.Restaurant.menu;

public class NumericStreams {

  public static int getMenuTotalCalories() {
    return menu.stream()
        .mapToInt(Dish::getCalories)
        .sum();
  }

  public static OptionalInt getMostCalorificDishInMenu() {
    return menu.stream()
        .mapToInt(Dish::getCalories)
        .max();
  }

  public static List<Dish> getDishesOfIncreasingCalorificValue() {
    return IntStream.rangeClosed(1, 100)
        .filter(n -> n % 2 == 0).limit(3)
        .mapToObj(
            i -> new Dish(MYSTERY_FISH, NOT_VEGETARIAN, i * 100, FISH)
        ).collect(toList());
  }

  public static List<Dish> getDishesOfIncreasingCalorificValue2() {
    return IntStream.rangeClosed(1, 100)
        .filter(n -> n % 2 == 0).limit(3)
        .boxed()
        .map(
            i -> new Dish(MYSTERY_FISH, NOT_VEGETARIAN, i * 100, FISH)
        ).collect(toList());
  }

  public static long getCountOfRange() {
    return IntStream.range(1, 100)
        .filter(n -> n % 2 == 0)
        .count();
  }

  public static long getCountOfRangeClosed() {
    return IntStream.rangeClosed(1, 100)
        .filter(n -> n % 2 == 0)
        .count();
  }

  public static List<int[]> getPythagoreanTriples() {
    return IntStream.rangeClosed(1, 100).boxed()
        .flatMap(a -> IntStream.rangeClosed(a, 100)
            .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
            .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
        )
        .limit(5)
        .collect(toList());
  }

  public static List<double[]> getPythagoreanTriplesBetter() {
    return IntStream.rangeClosed(1, 100).boxed()
        .flatMap(a ->
            IntStream.rangeClosed(a, 100)
                .mapToObj(
                    b -> new double[]{a, b, Math.sqrt(a * a + b * b)}
                )
                .filter(t -> t[2] % 1 == 0)
        )
        .limit(5).collect(toList());
  }

  public static boolean isPerfectSquare(int n) {
    return Math.sqrt(n) % 1 == 0;
  }
}