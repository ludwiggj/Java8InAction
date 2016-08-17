package lambdasinaction.chap2;

import lambdasinaction.chap1.Apple;

import java.util.*;

import static lambdasinaction.chap1.Apple.GREEN;

interface ApplePredicate {
  boolean test(Apple a);
}

// Take 7, abstract over list type
interface Predicate<T> {
  boolean test(T t);
}

public class FilteringApples {

  // Take 1
  public static List<Apple> filterGreenApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (GREEN.equals(apple.getColour())) {
        result.add(apple);
      }
    }
    return result;
  }

  // Take 2
  public static List<Apple> filterApplesByColour(List<Apple> inventory, String colour) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getColour().equals(colour)) {
        result.add(apple);
      }
    }
    return result;
  }

  // Take 2
  public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getWeight() > weight) {
        result.add(apple);
      }
    }
    return result;
  }

  // Take 4, filter by abstract criteria
  public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  // Take 7, abstract over list type
  public static <T> List<T> filterT(List<T> list, Predicate<T> p) {
    List<T> result = new ArrayList<>();
    for (T e : list) {
      if (p.test(e)) {
        result.add(e);
      }
    }
    return result;
  }
}