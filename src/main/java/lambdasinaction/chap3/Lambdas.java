package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Lambdas {

  public static void process(Runnable r) {
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

  public static <T> List<T> filter(List<T> list, Predicate<T> p) {
    List<T> results = new ArrayList<>();
    for (T s : list) {
      if (p.test(s)) {
        results.add(s);
      }
    }
    return results;
  }

  public static <T> void forEach(List<T> list, Consumer<T> c) {
    for (T i : list) {
      c.accept(i);
    }
  }

  public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
    List<R> result = new ArrayList<>();
    for (T s : list) {
      result.add(f.apply(s));
    }
    return result;
  }

  public static void main(String... args) {
    runnableRunout();

    forEach(Arrays.asList(1, 2, 3, 4, 5), i -> System.out.println(i));

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

    Supplier<String> eating = () -> "Raoul";

    Supplier<String> wii = () -> {
      return "Mario";
    };

    // This is incorrect...
    // Function<Integer, String> doobry = (Integer i) -> return "Alan" + i;

    // Alternatives
    Function<Integer, String> doobry = (Integer i) -> {
      return "Alan" + i;
    };
    Function<Integer, String> doobry2 = (Integer i) -> "Alan" + i;

    // This is incorrect...
    // UnaryOperator<String> un = (String s) -> {"Iron Man";}

    // Alternatives
    UnaryOperator<String> un = (String s) -> "Iron Man";
    UnaryOperator<String> un2 = (String s) -> {
      return "Iron Man";
    };
  }
}