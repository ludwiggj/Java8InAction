package lambdasinaction.chap8.template;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OnlineBankingTests {

  @Nested
  @DisplayName("Old style template")
  public class OldStyleTemplateTests {
    @Test
    @DisplayName("Make customer happy")
    public void shouldMakeCustomerHappy() {
      final String[] greeting = {""};

      (new OnlineBanking() {
        @Override
        void makeCustomerHappy(Customer c) {
          greeting[0] = String.format("Hello %s!", c.getName());
        }
      }).processCustomer(1337);

      assertThat(greeting[0], is("Hello Bob_1337!"));
    }
  }

  @Nested
  @DisplayName("Lambda template")
  public class LambdaTemplateTests {
    @Test
    @DisplayName("Make customer happy")
    public void shouldMakeCustomerHappy() {
      final String[] greeting = {""};

      new OnlineBankingLambda().processCustomer(
          1337, (Customer c) -> greeting[0] = String.format("Hello %s!", c.getName())
      );

      assertThat(greeting[0], is("Hello Bob_1337!"));
    }
  }
}