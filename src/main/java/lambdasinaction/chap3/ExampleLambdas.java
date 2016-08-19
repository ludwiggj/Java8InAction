package lambdasinaction.chap3;

public class ExampleLambdas {

  private static void process(Runnable r) {
    r.run();
  }

  private static void runnableRunout() {
    // Simple example
    Runnable r1 = () -> System.out.println("Hello!");

    process(r1);

    Runnable r2 = new Runnable() {
      public void run() {
        System.out.println("Hello World 2");
      }
    };

    process(r2);

    process(() -> System.out.println("Hello World 3"));
  }

  public static void main(String... args) {
    runnableRunout();

    int portNumber = 1337;
    Runnable r = () -> {
      System.out.println(portNumber);
      // Not allowed to modify local variable
      // portNumber = portNumber - 1;
    };
    r.run();

    // Not allowed. Variable used in lambda, so must be final or effectively final
    // portNumber += 1;

    // Quiz 3.1
    Runnable rr = () -> {
    };
  }
}