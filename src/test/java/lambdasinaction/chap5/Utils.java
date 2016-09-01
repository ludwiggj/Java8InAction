package lambdasinaction.chap5;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Utils {

  public static List<List<Integer>> toListOfListsOfIntegers(List<int[]> input) {
    return input.stream().map(
        arr -> Arrays.stream(arr)
            .boxed()
            .collect(toList())
    ).collect(toList());
  }

  static List<List<Double>> toListOfListsOfDoubles(List<double[]> input) {
    return input.stream().map(
        arr -> Arrays.stream(arr)
            .boxed()
            .collect(toList())
    ).collect(toList());
  }
}
