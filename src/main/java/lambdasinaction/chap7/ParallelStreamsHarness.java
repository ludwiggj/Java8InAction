package lambdasinaction.chap7;

import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

public class ParallelStreamsHarness {

    public static final String AVERAGE = "average";
    public static final String FASTEST = "fastest";

    public static void measurePerfAndDisplayResult(String methodName, Function<Long, Long> method) {
        Map<String, Long> results = measurePerf(method, 10_000_000L);
        System.out.println(
            String.format("%s: AVERAGE [%d ms], FASTEST [%d ms]", methodName, results.get(AVERAGE), results.get(FASTEST))
        );
    }

    public static void main(String[] args) {
        measurePerfAndDisplayResult("iterativeSum", ParallelStreams::iterativeSum);
        measurePerfAndDisplayResult("sequentialSum", ParallelStreams::sequentialSum);
        measurePerfAndDisplayResult("parallelSum", ParallelStreams::parallelSum);
        measurePerfAndDisplayResult("rangedSum", ParallelStreams::rangedSum);
        measurePerfAndDisplayResult("parallelRangedSum", ParallelStreams::parallelRangedSum);
        measurePerfAndDisplayResult("sideEffectSum", ParallelStreams::sideEffectSum);
        measurePerfAndDisplayResult("sideEffectParallelSum", ParallelStreams::sideEffectParallelSum);
        measurePerfAndDisplayResult("forkJoinSum", ForkJoinSumCalculator::forkJoinSum);
    }

    public static <T, R> Map<String, Long> measurePerf(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        long total = 0L;
        int iterations = 10;

        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + result);
            if (duration < fastest) fastest = duration;
            total += duration;
        }
        Map<String, Long> map = new HashMap<>();
        map.put(FASTEST, fastest);
        map.put(AVERAGE, total / iterations);

        return map;
    }
}
