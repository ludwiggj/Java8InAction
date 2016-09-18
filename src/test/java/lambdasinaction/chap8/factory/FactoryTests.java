package lambdasinaction.chap8.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.expectThrows;

public class FactoryTests {

  @Nested
  @DisplayName("Old style factory")
  class ProductFactoryOldStyleTests {
    @Test
    @DisplayName("Should get loan")
    public void shouldGetLoan() {
      Product p1 = ProductFactoryOldStyle.createProduct("loan");
      assertThat(p1, is(instanceOf(Loan.class)));
    }

    @Test
    @DisplayName("Should throw exception when ask for non-existent product")
    public void shouldThrowExceptionWhenAskForNonExistentProduct() {
      RuntimeException rte = expectThrows(RuntimeException.class,
          () -> ProductFactoryOldStyle.createProduct("blah")
      );

      assertThat(rte.getMessage(), is("No such product blah"));
    }
  }

  @Nested
  @DisplayName("Lambda factory")
  class ProductFactoryLambdaTests {
    @Test
    @DisplayName("Should get loan")
    public void shouldGetLoan() {
      Product p1 = ProductFactoryLambda.createProduct("loan");
      assertThat(p1, is(instanceOf(Loan.class)));
    }

    @Test
    @DisplayName("Should throw exception when ask for non-existent product")
    public void shouldThrowExceptionWhenAskForNonExistentProduct() {
      RuntimeException rte = expectThrows(RuntimeException.class,
          () -> ProductFactoryLambda.createProduct("blah")
      );

      assertThat(rte.getMessage(), is("No such product blah"));
    }
  }
}