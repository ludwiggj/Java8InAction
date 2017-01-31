package lambdasinaction.chap14;

import java.util.function.Function;

public class Integration {

  public static Function<Double, Double> differentiate(Function<Double, Double> func) {
    return null;
  }

  Function<Function<Double, Double>, Function<Double, Double>> x = Integration::differentiate;
}
