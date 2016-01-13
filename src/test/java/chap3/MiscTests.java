package chap3;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import static lambdasinaction.chap3.Lambdas.map;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Butterfly {
  static Integer sampleRate = 12;
  Integer count = 0;
}

public class MiscTests {


  @Test
  public void shouldMapStringsToLengthso() throws Exception {
    assertThat(map(Arrays.asList("lambdas", "in", "action"), s -> s.length()), is(Arrays.asList(7, 2, 6)));
  }

  // No boxing
  @Test
  public void evenOf1000ShouldBeTrue() {
    IntPredicate evenNumbers = (int i) -> i % 2 == 0;
    assertThat(evenNumbers.test(1000), is(true));
  }

  // Boxing
  @Test
  public void oddOf1000ShouldBeFalse() {
    Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
    assertThat(oddNumbers.test(1000), is(false));
  }

  @Test
  public void lamdbaCanCaptureAndModifyClassStaticVariable() throws Exception {
    Runnable r = () -> {
      assertThat(Butterfly.sampleRate, is(12));
      Butterfly.sampleRate += 1;
    };

    r.run();

    assertThat(Butterfly.sampleRate, is(13));
  }

  @Test
  public void lamdbaCanCaptureAndModifyClassInstanceVariable() throws Exception {
    Butterfly flutter = new Butterfly();
    Runnable r = () -> {
      assertThat(flutter.count, is(0));
      flutter.count += 1;
    };

    r.run();

    assertThat(flutter.count, is(1));
  }
}