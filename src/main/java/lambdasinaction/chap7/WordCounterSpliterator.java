package lambdasinaction.chap7;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {

  private final String string;
  private int currentChar = 0;

  public WordCounterSpliterator(String string) {
    this.string = string;
  }

  @Override
  public boolean tryAdvance(Consumer<? super Character> action) {
    action.accept(string.charAt(currentChar++));
    return currentChar < string.length();
  }

  @Override
  public Spliterator<Character> trySplit() {
    int currentSize = string.length() - currentChar;
    if (currentSize < 10) {
      return null;
    }
    for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
      if (Character.isWhitespace(string.charAt(splitPos))) {
        String stringPart1 = string.substring(currentChar, splitPos);
        String stringPart2 = string.substring(splitPos + 1);
        System.out.println(String.format("SPLITTING [%s]\ninto      [%s][%s]", stringPart1 + stringPart2, stringPart1, stringPart2));

        Spliterator<Character> spliterator = new WordCounterSpliterator(stringPart1);
        currentChar = splitPos;
        return spliterator;
      }
    }
    return null;
  }

  @Override
  public long estimateSize() {
    return string.length() - currentChar;
  }

  @Override
  public int characteristics() {
    return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
  }
}