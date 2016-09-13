package lambdasinaction.chap7;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCountParallelStream {

  public static int countWordsWithRandomSplit(String s) {
    // Use default split algorithm, which splits without respect to word boundaries
    Stream<Character> stream = IntStream.range(0, s.length()).mapToObj(s::charAt).parallel();
    return countWords(stream);
  }

  public static int countWordsWithSpliterator(String s) {
    Spliterator<Character> spliterator = new WordCounterSpliterator(s);
    Stream<Character> stream = StreamSupport.stream(spliterator, true);
    return countWords(stream);
  }

  private static int countWords(Stream<Character> stream) {
    WordCounter wordCounter = stream.reduce(new WordCounter(),
        WordCounter::accumulate,
        WordCounter::combine);

    System.out.println(wordCounter);

    return wordCounter.getCounter();
  }
}