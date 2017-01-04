package lambdasinaction.chap11.v1;

import java.util.Random;

import static lambdasinaction.chap11.Util.delay;

public class Shop {

  private final String name;
  private final Random random;

  public Shop(String name) {
    this.name = name;
    random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
  }

  // A synchronous method
  public double getPrice(String product) {
    return calculatePrice(product);
  }

  private double calculatePrice(String product) {
    delay();
    return random.nextDouble() * product.charAt(0) + product.charAt(1);
  }

  public String getName() {
    return name;
  }
}