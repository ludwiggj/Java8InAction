package lambdasinaction.chap7.benchmarks;

import lambdasinaction.chap7.WordCountIterative;
import lambdasinaction.chap7.WordCountParallelStream;
import lambdasinaction.chap7.WordCountStream;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)

// number of warm-up iterations (executed in both warm-up and measured forks)
@Warmup(iterations = 5)

// number of measured iterations (executed in both warm-up and measured forks)
@Measurement(iterations = 3)

// warmups = number of warm-up forks
// value = number of measured forks
@Fork(warmups = 4, value = 2)
public class WordCountBenchmarks {

  public static final String SENTENCE =
      " Nel   mezzo del cammin  di nostra  vita " +
          "mi  ritrovai in una  selva oscura" +
          " che la  dritta via era   smarrita ";

  @Benchmark
  public void countWordsIteratively() {
    System.out.println(WordCountIterative.countWordsIteratively(SENTENCE));
  }

  @Benchmark
  public void countWordsStream() {
    Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
    System.out.println(WordCountStream.countWordsStream(stream));
  }

  @Benchmark
  public void countWordsParallelStreamWithRandomSplit() {
    System.out.println(WordCountParallelStream.countWordsWithRandomSplit(SENTENCE));
  }

  @Benchmark
  public void countWordsParallelStreamWithSpliterator() {
    System.out.println(WordCountParallelStream.countWordsWithSpliterator(SENTENCE));
  }
}