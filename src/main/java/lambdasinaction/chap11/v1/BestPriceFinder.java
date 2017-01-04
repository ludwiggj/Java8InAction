package lambdasinaction.chap11.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lambdasinaction.chap11.v1.ExchangeService.Money;

import static lambdasinaction.chap11.v1.ExchangeService.Money.EUR;
import static lambdasinaction.chap11.v1.ExchangeService.Money.USD;

public class BestPriceFinder {

  private int numberOfShops = 4;
  private String[] shopNames = new String[]{
      "BestPrice", "LetsSaveBig", "MyFavoriteShop", "BuyItAll"
  };

  private List<Shop> shops() {
    System.out.println(String.format("Return a list of %d shops", numberOfShops));
    List<Shop> shops = new ArrayList<>();
    for (int i = 1; i <= numberOfShops; i++) {
      shops.add(new Shop(String.format("%s(Shop%d)", shopNames[i % shopNames.length], i)));
    }
    return shops;
  }

  public List<String> findPricesSequential(String product) {
    return shops().stream()
        .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
        .collect(Collectors.toList());
  }

  public List<String> findPricesSequential(String product, int numberOfShops) {
    this.numberOfShops = numberOfShops;
    return findPricesSequential(product);
  }

  public List<String> findPricesParallel(String product) {
    return shops().parallelStream()
        .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
        .collect(Collectors.toList());
  }

  public List<String> findPricesParallel(String product, int numberOfShops) {
    this.numberOfShops = numberOfShops;
    return findPricesParallel(product);
  }

  public List<String> findPricesFuture(String product) {
    List<CompletableFuture<String>> priceFutures = shops().stream()
        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product)))
        .collect(Collectors.toList());

    List<String> prices = priceFutures.stream()
        .map(CompletableFuture::join)
        .collect(Collectors.toList());
    return prices;
  }

  public List<String> findPricesFuture(String product, int numberOfShops) {
    this.numberOfShops = numberOfShops;
    return findPricesFuture(product);
  }

  public List<String> findPricesFutureWithCustomExecutor(String product, int numberOfShops) {
    this.numberOfShops = numberOfShops;
    List<Shop> shops = shops();

    final Executor executor = Executors.newFixedThreadPool(shops.size(), r -> {
      Thread t = new Thread(r);
      t.setDaemon(true);
      return t;
    });

    List<CompletableFuture<String>> priceFutures = shops.stream()
        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product), executor))
        .collect(Collectors.toList());

    List<String> prices = priceFutures.stream()
        .map(CompletableFuture::join)
        .collect(Collectors.toList());
    return prices;
  }

  // This is fast as expected
  public List<String> findPricesInUSDJava7(String product) {
    ExecutorService executor = Executors.newCachedThreadPool();
    List<Future<Double>> priceFutures = new ArrayList<>();
    for (Shop shop : shops()) {
      final Future<Double> futureRate = executor.submit(new Callable<Double>() {
        public Double call() {
          return ExchangeService.getRate(Money.EUR, Money.USD);
        }
      });
      Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {
        public Double call() {
          try {
            double priceInEUR = shop.getPrice(product);
            return priceInEUR * futureRate.get();
          } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e.getMessage(), e);
          }
        }
      });
      priceFutures.add(futurePriceInUSD);
    }
    List<String> prices = new ArrayList<>();
    for (Future<Double> priceFuture : priceFutures) {
      try {
        prices.add(/*shop.getName() +*/ " price is " + priceFuture.get());
      } catch (ExecutionException | InterruptedException e) {
        e.printStackTrace();
      }
    }
    return prices;
  }

  // This is fast as expected
  public List<String> findPriceInUSD(String product) {

    Shop s = new Shop("AShop");
    CompletableFuture<Double> futurePriceInUSD = CompletableFuture.supplyAsync(() -> s.getPrice(product))
        .thenCombine(
            CompletableFuture.supplyAsync(() -> ExchangeService.getRate(EUR, USD)),
            (price, rate) -> price * rate
        );

    List<String> result = new ArrayList<>();
    try {
      result.add(futurePriceInUSD.get(5, TimeUnit.SECONDS).toString());
    } catch (Exception e) {
      e.printStackTrace();  //TODO: Auto-generated
    }
    return result;
  }

  //TODO This is slow; why?
  public List<String> findPricesInUSD(String product) {
    List<CompletableFuture<Double>> priceFutures = new ArrayList<>();

    List<Shop> shops = shops();

    final Executor executor = Executors.newFixedThreadPool(shops.size(), r -> {
      Thread t = new Thread(r);
      t.setDaemon(true);
      return t;
    });

    for (Shop shop : shops) {
      CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor);
      CompletableFuture<Double> futureRate = CompletableFuture.supplyAsync(() -> ExchangeService.getRate(EUR, USD), executor);

      CompletableFuture<Double> futurePriceInUSD = futurePrice.thenCombine(
          futureRate,
          (price, rate) -> price * rate
      );
      priceFutures.add(futurePriceInUSD);
    }

    // Drawback: The shop is not accessible anymore outside the loop,
    // so the getName() call below has been commented out.
    List<String> prices = priceFutures
        .stream()
        .map(CompletableFuture::join)
        .map(price -> /*shop.getName() +*/ " price is " + price)
        .collect(Collectors.toList());
    return prices;
  }

  //TODO This is slow; why?
  public List<String> findPricesInUSD2(String product) {
    List<CompletableFuture<String>> priceFutures = new ArrayList<>();
    for (Shop shop : shops()) {
      // Here, an extra operation has been added so that the shop name
      // is retrieved within the loop. As a result, we now deal with
      // CompletableFuture<String> instances.
      CompletableFuture<String> futurePriceInUSD = CompletableFuture.supplyAsync(() -> shop.getPrice(product))
          .thenCombine(
              CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD)),
              (price, rate) -> price * rate
          ).thenApply(price -> shop.getName() + " price is " + price);
      priceFutures.add(futurePriceInUSD);
    }

    List<String> prices = priceFutures
        .stream()
        .map(CompletableFuture::join)
        .collect(Collectors.toList());
    return prices;
  }

  //TODO This is slow; why?
  public List<String> findPricesInUSD3(String product) {
    // Here, the for loop has been replaced by a mapping function...
    Stream<CompletableFuture<String>> priceFuturesStream = shops()
        .stream()
        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product))
            .thenCombine(
                CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD)),
                (price, rate) -> price * rate)
            .thenApply(price -> shop.getName() + " price is " + price)
        );

    // However, we should gather the CompletableFutures into a List so that the asynchronous
    // operations are triggered before being "joined."
    List<CompletableFuture<String>> priceFutures = priceFuturesStream.collect(Collectors.toList());

    List<String> prices = priceFutures
        .stream()
        .map(CompletableFuture::join)
        .collect(Collectors.toList());

    return prices;
  }
}