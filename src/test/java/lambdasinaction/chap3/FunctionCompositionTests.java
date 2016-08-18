package lambdasinaction.chap3;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FunctionCompositionTests {
  private Function<Integer, Integer> f = x -> x + 1;
  private Function<Integer, Integer> g = x -> x * 2;
  private Function<String, String> addHeader = Letter::addHeader;

  @Test
  public void shouldAndThenFunctions() {
    Function<Integer, Integer> h = f.andThen(g);

    assertThat(h.apply(1), is(4));
    assertThat(h.apply(2), is(6));
    assertThat(h.apply(3), is(8));
  }

  @Test
  public void shouldComposeFunctions() {
    Function<Integer, Integer> h = f.compose(g);

    assertThat(h.apply(1), is(3));
    assertThat(h.apply(2), is(5));
    assertThat(h.apply(3), is(7));
  }

  @Test
  public void shouldWriteLetter() throws Exception {
    Function<String, String> transformationPipeline = addHeader.andThen(Letter::addFooter);

    String expectedLetter = "From Raoul, Mario and Alan: Hi! Kind regardz";

    assertThat(transformationPipeline.apply("Hi!"), is(expectedLetter));
  }

  @Test
  public void shouldWriteAndSpellCheckLetter() throws Exception {
    Function<String, String> transformationPipeline =
        addHeader.andThen(Letter::addFooter).andThen(Letter::checkSpelling);

    String expectedLetter = "From Raoul, Mario and Alan: Hi! Kind regards";

    assertThat(transformationPipeline.apply("Hi!"), is(expectedLetter));
  }
}