package lambdasinaction.chap3;

import lambdasinaction.chap1.Apple;

import java.util.function.*;

public class ExampleLambda {
  public static void main(String[] args) {

    ToIntFunction<String> abc = (String s) -> s.length();

    System.out.println(abc.applyAsInt("Hello"));

    Predicate<Apple> heavyApple = (Apple a) -> a.getWeight() > 150;

    System.out.println(heavyApple.test(new Apple(150, "green")));
    System.out.println(heavyApple.test(new Apple(151, "russet")));

    BiConsumer<Integer, Integer> adder = (Integer x, Integer y) -> {
      System.out.println("Result:");
      System.out.println(x + y);
    };

    adder.accept(1, 3);

    IntSupplier sup = () -> 42;

    System.out.println(sup.getAsInt());

    BiFunction<Apple, Apple, Integer> bif =
        (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

    System.out.println(bif.apply(new Apple(234, "red"), new Apple(154, "green")));
  }
}
