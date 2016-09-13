package lambdasinaction.chap7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static lambdasinaction.chap7.WordCountIterative.countWordsIteratively;
import static lambdasinaction.chap7.WordCountParallelStream.*;
import static lambdasinaction.chap7.WordCountStream.countWordsStream;
import static lambdasinaction.chap7.benchmarks.WordCountBenchmarks.SENTENCE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class WordCountTests {

  @Test
  @DisplayName("Count words iteratively")
  public void shouldCountWordsIteratively() {
    assertThat(countWordsIteratively(SENTENCE), is(19));
  }

  @Test
  @DisplayName("Count words stream")
  public void shouldCountWordsStream() {
    Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
    assertThat(countWordsStream(stream), is(19));
  }

  @Test
  @DisplayName("Count words parallel stream with random split")
  public void shouldCountWordsParallelStreamWithRandomSplit() {
    assertThat(countWordsWithRandomSplit(SENTENCE), is(not(19)));
  }

  @Test
  @DisplayName("Count words parallel stream with spliterator")
  public void shouldCountWordsParallelStreamWithSpliterator() {
    assertThat(countWordsWithSpliterator(SENTENCE), is(19));
  }
}