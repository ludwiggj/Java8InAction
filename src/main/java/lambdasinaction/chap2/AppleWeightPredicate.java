package lambdasinaction.chap2;

import lambdasinaction.chap1.Apple;

public class AppleWeightPredicate implements ApplePredicate {
  public boolean test(Apple apple) {
    return apple.getWeight() > 150;
  }
}