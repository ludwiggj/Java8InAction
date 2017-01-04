package lambdasinaction.chap8.chain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextProcessingTests {

  private static final String INPUT = "Aren't labdas really sexy?!!";
  private static final String EXPECTED_OUTPUT =
      "From Raoul, Mario and Alan: Aren't lambdas really sexy?!!";

  @Nested
  @DisplayName("Text processing old style")
  public class TextProcessingOldStyleTests {
    @Test
    @DisplayName("Should process text")
    public void shouldProcessText() {
      ProcessingObject<String> p1 = new HeaderTextProcessing();
      ProcessingObject<String> p2 = new SpellCheckerProcessing();
      p1.setSuccessor(p2);
      assertThat(p1.handle(INPUT), is(EXPECTED_OUTPUT));
    }
  }

  @Nested
  @DisplayName("Text processing lambdas")
  public class TextProcessingLambdaTests {
    @Test
    @DisplayName("Should process text")
    public void shouldProcessText() {
      UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
      UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");

      Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

      assertThat(pipeline.apply(INPUT), is(EXPECTED_OUTPUT));
    }
  }
}