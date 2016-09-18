package lambdasinaction.chap8.observer;


public class ObserverLambda {

  public static void main(String[] args) {
    Feed feedLambda = new Feed();

    feedLambda.registerObserver((String tweet) -> {
      if (tweet != null && tweet.contains("money")) {
        System.out.println("Breaking news in NY! " + tweet);
      }
    });

    feedLambda.registerObserver((String tweet) -> {
      if (tweet != null && tweet.contains("queen")) {
        System.out.println("Yet another news in London... " + tweet);
      }
    });

    feedLambda.notifyObservers("Money money money, give me money!");
  }
}