package lambdasinaction.chap7.benchmarks;

import lambdasinaction.chap7.ForkJoinSumCalculator;
import lambdasinaction.chap7.ParallelStreams;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)

// number of warm-up iterations (executed in both warm-up and measured forks)
@Warmup(iterations = 5)

// number of measured iterations (executed in both warm-up and measured forks)
@Measurement(iterations = 3)

// warmups = number of warm-up forks
// value = number of measured forks
@Fork(warmups = 4, value = 2)
public class ParallelStreamBenchmarks {

  @Benchmark
  public void sequentialSum() {
    ParallelStreams.sequentialSum(10_000_000L);
  }

  @Benchmark
  public void iterativeSum() {
    ParallelStreams.iterativeSum(10_000_000L);
  }

  @Benchmark
  public void parallelSum() {
    ParallelStreams.parallelSum(10_000_000L);
  }

  @Benchmark
  public void rangedSum() {
    ParallelStreams.rangedSum(10_000_000L);
  }

  @Benchmark
  public void parallelRangedSum() {
    ParallelStreams.parallelRangedSum(10_000_000L);
  }

  @Benchmark
  public void sideEffectSum() {
    ParallelStreams.sideEffectSum(10_000_000L);
  }

  @Benchmark
  public void sideEffectParallelSum() {
    ParallelStreams.sideEffectParallelSum(10_000_000L);
  }

  @Benchmark
  public void forkJoinSum() {
    ForkJoinSumCalculator.forkJoinSum(10_000_000L);
  }
}