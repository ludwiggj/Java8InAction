package lambdasinaction.chap6;

import java.util.function.*;

public class CollectorHarness {

  public static void main(String[] args) {
    System.out.println("Partitioning done in: " + execute(PartitionPrimeNumbers::partitionPrimesTake2) + " msecs");
    System.out.println("Partitioning done in: " + execute(PartitionPrimeNumbersCustomCollector::partitionPrimesWithCustomCollector) + " msecs");
  }

  private static long execute(Consumer<Integer> primePartitioner) {
    long fastest = Long.MAX_VALUE;
    for (int i = 0; i < 10; i++) {
      long start = System.nanoTime();
      primePartitioner.accept(10_000_000);
      long duration = (System.nanoTime() - start) / 1_000_000;
      if (duration < fastest) fastest = duration;
      System.out.println("done in " + duration);
    }
    return fastest;
  }
}