package lambdasinaction.chap14;

import java.util.function.DoubleUnaryOperator;

public class Currying {

  static double converter(double x, double conversionFactor, double baseline) {
    return x * conversionFactor + baseline;
  }

  static DoubleUnaryOperator curriedConverter(double y, double z) {
    return (double x) -> x * y + z;
  }
}