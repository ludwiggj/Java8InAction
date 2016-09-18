package lambdasinaction.chap8;

import lambdasinaction.chap8.Strategy.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StrategyTests {

  private static final String ALL_LOWER_CASE = "thisisalllowercase";
  private static final String MIXED_CASE = "ThisIsMixedCase";
  private static final String ALL_NUMERIC = "123456789";
  private static final String ALPHA_NUMERIC = "A12345C6789";

  @Nested
  @DisplayName("Old style strategy tests")
  public class OldStyleStrategyTests {
    @DisplayName("Lower case validation")
    @Test
    public void shouldValidateLowerCase() {
      Validator validatorOldStyle = new Validator(new IsAllLowerCase());
      assertAll("Lower case validation",
          () -> assertThat(validatorOldStyle.validate(ALL_LOWER_CASE), is(true)),
          () -> assertThat(validatorOldStyle.validate(MIXED_CASE), is(false))
      );
    }

    @DisplayName("Is numeric validation")
    @Test
    public void shouldValidateNumerical() {
      Validator validatorOldStyle = new Validator(new IsNumeric());
      assertAll("Is numeric validation",
          () -> assertThat(validatorOldStyle.validate(ALL_NUMERIC), is(true)),
          () -> assertThat(validatorOldStyle.validate(ALPHA_NUMERIC), is(false))
      );
    }
  }

  @Nested
  @DisplayName("Lambda strategy tests")
  public class LambdaStrategyTests {
    @DisplayName("Lower case validation")
    @Test
    public void shouldValidateLowerCase() {
      Validator validatorLambda = new Validator((String s) -> s.matches("[a-z]+"));
      assertAll("Lower case validation",
          () -> assertThat(validatorLambda.validate(ALL_LOWER_CASE), is(true)),
          () -> assertThat(validatorLambda.validate(MIXED_CASE), is(false))
      );
    }

    @DisplayName("Is numeric validation")
    @Test
    public void shouldValidateNumerical() {
      Validator validatorLambda = new Validator((String s) -> s.matches("\\d+"));
      assertAll("Is numeric validation",
          () -> assertThat(validatorLambda.validate(ALL_NUMERIC), is(true)),
          () -> assertThat(validatorLambda.validate(ALPHA_NUMERIC), is(false))
      );
    }
  }
}