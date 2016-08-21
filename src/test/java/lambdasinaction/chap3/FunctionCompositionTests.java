package lambdasinaction.chap3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FunctionCompositionTests {
  private Function<Integer, Integer> f = x -> x + 1;
  private Function<Integer, Integer> g = x -> x * 2;
  private Function<String, String> addHeader = Letter::addHeader;

  @Test
  @DisplayName("f andThen g tests")
  public void shouldAndThenFunctions() {
    Function<Integer, Integer> h = f.andThen(g);

    assertAll("andThen",
        () -> assertThat(h.apply(1), is(4)),
        () -> assertThat(h.apply(2), is(6)),
        () -> assertThat(h.apply(3), is(8))
    );
  }

  @Test
  @DisplayName("f compose g tests")
  public void shouldComposeFunctions() {
    Function<Integer, Integer> h = f.compose(g);

    assertAll("compose",
        () -> assertThat(h.apply(1), is(3)),
        () -> assertThat(h.apply(2), is(5)),
        () -> assertThat(h.apply(3), is(7))
    );
  }

  @Test
  @DisplayName("should write a letter")
  public void shouldWriteLetter() {
    Function<String, String> transformationPipeline = addHeader.andThen(Letter::addFooter);

    String expectedLetter = "From Raoul, Mario and Alan: Hi! Kind regardz";

    assertThat(transformationPipeline.apply("Hi!"), is(expectedLetter));
  }

  @Test
  @DisplayName("should write and spell check letter")
  public void shouldWriteAndSpellCheckLetter() {
    Function<String, String> transformationPipeline =
        addHeader.andThen(Letter::addFooter).andThen(Letter::checkSpelling);

    String expectedLetter = "From Raoul, Mario and Alan: Hi! Kind regards";

    assertThat(transformationPipeline.apply("Hi!"), is(expectedLetter));
  }
}