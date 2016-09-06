package lambdasinaction.chap6;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class PartitionPrimeNumbers {

  private static Map<Boolean, List<Integer>> partitionPrimes(int n, Predicate<Integer> isPrime) {
    return IntStream.rangeClosed(2, n).boxed()
        .collect(partitioningBy(candidate -> isPrime.test(candidate)));
  }

  public static Map<Boolean, List<Integer>> partitionPrimesTake1(int n) {
    return partitionPrimes(n, isPrimeTake1);
  }

  public static Map<Boolean, List<Integer>> partitionPrimesTake2(int n) {
    return partitionPrimes(n, isPrimeTake2);
  }

  public static Map<Boolean, List<Integer>> partitionPrimesTake3(int n) {
    return partitionPrimes(n, isPrimeTake3);
  }

  private static Predicate<Integer> isPrimeTake1 = candidate ->
      IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);

  private static Predicate<Integer> isPrimeTake2= candidate -> {
    int candidateRoot = (int) Math.sqrt((double) candidate);
    return IntStream.rangeClosed(2, candidateRoot)
        .noneMatch(i -> candidate % i == 0);
  };

  private static Predicate<Integer> isPrimeTake3 = candidate -> {
    double candidateRoot = Math.floor(Math.sqrt((double) candidate));
    return IntStream.rangeClosed(2, candidate - 1)
        .limit((long) candidateRoot - 1)
        .noneMatch(i -> candidate % i == 0);
  };
}