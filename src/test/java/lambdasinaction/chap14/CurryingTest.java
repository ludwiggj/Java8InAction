package lambdasinaction.chap14;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.DoubleUnaryOperator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class CurryingTest {

  @Test
  @DisplayName("Should convert Celsius to Fahrenheit")
  public void celsiusToFahrenheit() {
    double waterBoilingPointInF = Currying.converter(100, 9.0 / 5, 32);
    assertThat(waterBoilingPointInF, is(212.0));

    DoubleUnaryOperator convertCtoF = Currying.curriedConverter(9.0 / 5, 32);
    assertThat(convertCtoF.applyAsDouble(100.0), is(212.0));
  }

  @Test
  @DisplayName("Should convert kilometers to miles")
  public void kilometersToMiles() {
    DoubleUnaryOperator kilometersToMiles = Currying.curriedConverter(0.6214, 0);
    assertThat(kilometersToMiles.applyAsDouble(10.0), is(closeTo(6.21, 0.01)));
  }
}