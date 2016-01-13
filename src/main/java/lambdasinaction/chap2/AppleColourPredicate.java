package lambdasinaction.chap2;

import lambdasinaction.chap1.Apple;

public class AppleColourPredicate implements ApplePredicate {
  public boolean test(Apple apple) {
    return "green".equals(apple.getColour());
  }
}