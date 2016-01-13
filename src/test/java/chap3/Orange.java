package chap3;

import lambdasinaction.chap1.Fruit;

public class Orange extends Fruit {
  public static final String DEFAULT_COLOUR = "ORANGE";

  public Orange(int weight) {
    super(weight, DEFAULT_COLOUR);
  }

  public String toString() {
    return "Orange {" + "colour='" + colour + '\'' + ", weight=" + weight + '}';
  }
}
