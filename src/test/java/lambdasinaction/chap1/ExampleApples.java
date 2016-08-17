package lambdasinaction.chap1;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap1.Apple.GREEN;
import static lambdasinaction.chap1.Apple.RED;

public class ExampleApples {
  private ExampleApples() {
  }

  public static final Apple APPLE_GREEN_80 = new Apple(80, GREEN);
  public static final Apple APPLE_GREEN_155 = new Apple(155, GREEN);
  public static final Apple APPLE_RED_120 = new Apple(120, RED);

  public static final List<Apple> GREEN_APPLES = Arrays.asList(APPLE_GREEN_80, APPLE_GREEN_155);

  public static final List<Apple> RED_APPLES = Arrays.asList(APPLE_RED_120);

  public static final List<Apple> HEAVY_APPLES = Arrays.asList(APPLE_GREEN_155);
}
