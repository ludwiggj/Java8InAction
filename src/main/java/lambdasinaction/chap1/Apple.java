package lambdasinaction.chap1;

public class Apple extends Fruit {
  public static final int HEAVY_WEIGHT = 150;
  public static final int DEFAULT_WEIGHT = 20;

  public static final String GREEN = "green";
  public static final String RED = "red";
  public static final String DEFAULT_COLOUR = GREEN;

  public Apple() {
    this(DEFAULT_WEIGHT, DEFAULT_COLOUR);
  }

  public Apple(int weight, String colour) {
    super(weight, colour);
  }

  public Apple(int weight) {
    this(weight, DEFAULT_COLOUR);
  }

  public String toString() {
    return "Apple {" + "colour='" + colour + '\'' + ", weight=" + weight + '}';
  }

  public boolean isHeavy() {
      return getWeight() > HEAVY_WEIGHT;
    }
}