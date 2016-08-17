package lambdasinaction.chap2;

import lambdasinaction.chap1.Apple;

import java.util.List;

interface AppleFormatter {
  String accept(Apple apple);
}

public class PrintingApples {
  public static String prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
    String result = "";
    for (Apple apple : inventory) {
      result += formatter.accept(apple) + "|";
    }
    return result;
  }
}