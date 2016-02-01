package lambdasinaction.chap5;

import java.io.IOException;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.*;
import java.nio.charset.Charset;
import java.nio.file.*;

import static java.util.stream.Collectors.toList;

public class BuildingStreams {

  public static void main(String... args) throws Exception {
    // random stream of doubles with Stream.generate
    Stream.generate(Math::random)
        .limit(10)
        .forEach(System.out::println);
  }

  public static Stream<String> getStreamFromStringArgs() {
    return Stream.of("Java 8", "Lambdas", "In", "Action").map(String::toUpperCase);
  }

  public static Stream<String> getEmptyStream() {
    return Stream.empty();
  }

  public static int getArraySum() {
    int[] numbers = {2, 3, 5, 7, 11, 13};
    return Arrays.stream(numbers).sum();
  }

  public static long getNumberOfUniqueWordsInFile() throws IOException {
    return Files.lines(
        Paths.get("src/main/resources/lambdasinaction/chap5/data.txt"), Charset.defaultCharset()
    )
        .flatMap(line -> Arrays.stream(line.split(" ")))
        .distinct()
        .count();
  }

  public static Stream<Integer> getStreamViaIterate() {
    return Stream.iterate(0, n -> n + 2)
        .limit(10);
  }

  public static List<int[]> generateFibonacciSequencePairs() {
    return Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
        .limit(10)
        .collect(toList());
  }

  public static List<Integer> generateFibonacciSequence() {
    return Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
        .limit(10)
        .map(t -> t[0])
        .collect(toList());
  }

  public static IntStream generateAStreamOfFiveOnes() {
    return IntStream.generate(() -> 1).limit(5);
  }

  public static IntStream generateAStreamOfFiveTwos() {
    return IntStream.generate(new IntSupplier() {
      public int getAsInt() {
        return 2;
      }
    }).limit(5);
  }

  public static IntStream generateFibonacciSequenceStream() {
    IntSupplier fib = new IntSupplier() {
      private int previous = 0;
      private int current = 1;

      public int getAsInt() {
        int oldPrevious = this.previous;
        int nextValue = this.previous + this.current;
        this.previous = this.current;
        this.current = nextValue;
        return oldPrevious;
      }
    };
    return IntStream.generate(fib).limit(10);
  }
}