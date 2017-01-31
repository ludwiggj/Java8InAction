package lambdasinaction.chap14;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MyMathsUtils {
  public static boolean isPrime(int candidate) {
    int candidateRoot = (int) Math.sqrt((double) candidate);
    return IntStream.rangeClosed(2, candidateRoot)
        .noneMatch(i -> candidate % i == 0);
  }

  public static Stream<Integer> primes(int n) {
    return Stream.iterate(2, i -> i + 1)
        .filter(MyMathsUtils::isPrime)
        .limit(n);
  }

  // Build same solution up from scratch...

  static IntStream numbers() {
    return IntStream.iterate(2, n -> n + 1);
  }

  static int head(IntStream numbers) {
    // findFirst is a terminal operation
    return numbers.findFirst().getAsInt();
  }

  static IntStream tail(IntStream numbers) {
    // skip is a terminal operation
    return numbers.skip(1);
  }

  // This function will generate a run-time exception
  static IntStream removeHeadAndRemoveMultiplesFromStream(IntStream numbers) {
    // Given the head of the stream, the numbers can be filtered as follows:
    int head = head(numbers);

    System.out.println("Removed head [" + head + "]");

    // Next line will cause an exception, as the stream has already been processed!
    IntStream tail = tail(numbers);

    IntStream filtered = tail.filter(n -> n % head != 0);

    return filtered;
  }

  // This function will also generate a run-time exception
  static IntStream primes(IntStream numbers) {
    int head = head(numbers);
    return IntStream.concat(
        IntStream.of(head),
        // This is a recursive call which would be infinite (if it didn't already give a run-time exception :)
        primes(tail(numbers).filter(n -> n % head != 0))
    );
  }

  public static void main(String[] args) {
    System.out.println(primes(5).collect(toList()));

//    IntStream filtered = removeHeadAndRemoveMultiplesFromStream(numbers());
//    System.out.println(String.format("Next five numbers %s", filtered.limit(5)));

//    IntStream primes = primes(numbers());
  }
}