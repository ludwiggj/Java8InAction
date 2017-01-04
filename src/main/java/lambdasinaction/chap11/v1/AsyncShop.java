package lambdasinaction.chap11.v1;

import lambdasinaction.chap11.Util;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static lambdasinaction.chap11.Util.delay;

public class AsyncShop {

  private final String name;
  private final Random random;
  private boolean faulty = false;

  public AsyncShop(String name) {
    this.name = name;
    random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
  }

  public AsyncShop(String name, boolean faulty) {
    this(name);
    this.faulty = faulty;
  }

  public Future<Double> getPrice(String product) {
    return CompletableFuture.supplyAsync(() -> calculatePrice(product));
  }

  public Future<Double> getPrice2(String product) {
    CompletableFuture<Double> futurePrice = new CompletableFuture<>();
    new Thread(() -> {
      try {
        double price = calculatePrice(product);
        futurePrice.complete(price);
      } catch (Exception ex) {
        futurePrice.completeExceptionally(ex);
      }
    }).start();
    return futurePrice;
  }

  private double calculatePrice(String product) {
    delay();
    if (faulty) throw new RuntimeException("product not available");
    return Util.format(random.nextDouble() * product.charAt(0) + product.charAt(1));
  }
}