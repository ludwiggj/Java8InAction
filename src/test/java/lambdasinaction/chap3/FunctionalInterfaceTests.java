package lambdasinaction.chap3;

import lambdasinaction.chap1.Apple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.*;
import java.util.function.Predicate;

import static lambdasinaction.chap1.Apple.GREEN;
import static lambdasinaction.chap1.ExampleApples.APPLE_GREEN_155;
import static lambdasinaction.chap1.ExampleApples.APPLE_GREEN_80;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class Butterfly {
  static Integer sampleRate = 12;
  Integer count = 0;
}

public class FunctionalInterfaceTests {

  @Nested
  @DisplayName("Predicate tests")
  class PredicateTests {

    @Test
    @DisplayName("IntPredicate, no boxing")
    public void evenOf1000ShouldBeTrue() {
      IntPredicate evenNumbers = (int i) -> i % 2 == 0;
      assertThat(evenNumbers.test(1000), is(true));
    }

    @Test
    @DisplayName("Predicate<Integer>, boxing")
    public void oddOf1000ShouldBeFalse() {
      Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
      assertThat(oddNumbers.test(1000), is(false));
    }

    @Test
    @DisplayName("Apples")
    public void heavyApples() {
      Predicate<Apple> heavyApple = (Apple a) -> a.getWeight() > 150;

      assertAll("Heavy tests",
          () -> assertThat(heavyApple.test(new Apple(150, GREEN)), is(false)),
          () -> assertThat(heavyApple.test(new Apple(151, GREEN)), is(true))
      );
    }

    @Test
    @DisplayName("Compare apples")
    public void compareApplesTest() {
      // The same lambda can be used with multiple different functional interfaces
      Comparator<Apple> comp = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

      BiFunction<Apple, Apple, Integer> bif =
          (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

      ToIntBiFunction<Apple, Apple> toIntBif =
          (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

      assertAll("Compare tests",
          () -> assertThat(comp.compare(APPLE_GREEN_80, APPLE_GREEN_155), is(-1)),
          () -> assertThat(bif.apply(APPLE_GREEN_80, APPLE_GREEN_155), is(-1)),
          () -> assertThat(toIntBif.applyAsInt(APPLE_GREEN_155, APPLE_GREEN_80), is(1))
      );
    }
  }

  @Nested
  @DisplayName("Function tests")
  class FunctionTests {
    @Test
    @DisplayName("ToIntFunction")
    public void toIntFunctionTest() {
      ToIntFunction<String> abc = (String s) -> s.length();

      assertThat(abc.applyAsInt("Hello"), is(5));
    }

    @Test
    @DisplayName("Alan")
    public void alanTest() {

      // This is incorrect...
      // Function<Integer, String> doobry = (Integer i) -> return "Alan" + i;

      // Two alternatives

      Function<Integer, String> doobry = (Integer i) -> {
        return "Alan" + i;
      };

      Function<Integer, String> doobry2 = (Integer i) -> "Alan" + i;

      assertAll("Alan",
          () -> assertThat(doobry.apply(4), is("Alan4")),
          () -> assertThat(doobry2.apply(3), is("Alan3"))
      );
    }
  }

  @Nested
  @DisplayName("Consumer tests")
  class ConsumerTests {
    @Test
    @DisplayName("BiConsumer")
    public void biConsumerTest() {
      final int[] total = {0};
      BiConsumer<Integer, Integer> adder = (Integer x, Integer y) -> total[0] = x + y;
      adder.accept(1, 3);

      assertThat(total, is(new int[]{4}));
    }
  }

  @Nested
  @DisplayName("Supplier tests")
  class SupplierTests {

    @Test
    @DisplayName("IntSupplier")
    public void intSupplierTest() {
      IntSupplier sup = () -> 42;

      assertThat(sup.getAsInt(), is(42));
    }

    @Test
    @DisplayName("String supplier")
    public void stringSupplierTests() {
      Supplier<String> eating = () -> "Raoul";

      Supplier<String> wii = () -> {
        return "Mario";
      };

      assertAll("String suppliers",
          () -> assertThat(eating.get(), is("Raoul")),
          () -> assertThat(wii.get(), is("Mario"))
      );
    }
  }

  @Nested
  @DisplayName("Unary op tests")
  class UnaryOpTests {
    @Test
    @DisplayName("Who is Iron Man?")
    public void ironTest() {
      // This is incorrect...
      // UnaryOperator<String> un = (String s) -> {"Iron Man";}

      // Alternatives
      UnaryOperator<String> un = (String s) -> "Iron Man";
      UnaryOperator<String> un2 = (String s) -> {
        return "Iron Man";
      };

      assertAll("Who is Iron Man",
          () -> assertThat(un.apply("Clark Kent"), is("Iron Man")),
          () -> assertThat(un2.apply("Joe Spud"), is("Iron Man"))
      );
    }
  }

  @Nested
  @DisplayName("Lambdas and Variables")
  class LambdaVariables {
    @Test
    @DisplayName("Lamdba can modify class static variable")
    public void lamdbaCanCaptureAndModifyClassStaticVariable() {
      Runnable r = () -> {
        assertThat(Butterfly.sampleRate, is(12));
        Butterfly.sampleRate += 1;
      };

      r.run();

      assertThat(Butterfly.sampleRate, is(13));
    }

    @Test
    @DisplayName("Lamdba can modify class instance variable")
    public void lamdbaCanCaptureAndModifyClassInstanceVariable() {
      Butterfly flutter = new Butterfly();
      Runnable r = () -> {
        assertThat(flutter.count, is(0));
        flutter.count += 1;
      };

      r.run();

      assertThat(flutter.count, is(1));
    }
  }
}