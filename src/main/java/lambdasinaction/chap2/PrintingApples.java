package lambdasinaction.chap2;

import lambdasinaction.chap1.Apple;

import java.util.Arrays;
import java.util.List;

public class PrintingApples {

  public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
    for (Apple apple : inventory) {
      String output = formatter.accept(apple);
      System.out.println(output);
    }
  }

  public static void main(String[] args) {
    AppleFormatter colourAndWeight = new AppleFormatter() {
      @Override
      public String accept(Apple apple) {
        return String.format("Colour %s, weight %s", apple.getColour(), apple.getWeight());
      }
    };

    class AppleFancyFormatter implements AppleFormatter {
      public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColour() + " apple";
      }
    }

    class AppleSimpleFormatter implements AppleFormatter {
      public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
      }
    }

    List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

    prettyPrintApple(inventory, colourAndWeight);
    prettyPrintApple(inventory, new AppleFancyFormatter());
    prettyPrintApple(inventory, new AppleSimpleFormatter());
  }
}