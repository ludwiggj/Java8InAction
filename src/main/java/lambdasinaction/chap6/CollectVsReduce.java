package lambdasinaction.chap6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectVsReduce {

  public static List<Integer> streamToListViaReduce() {
    Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();

    List<Integer> numbers = stream.reduce(
        new ArrayList<>(),
        (List<Integer> l, Integer e) -> {
          l.add(e);
          return l;
        },
        (List<Integer> l1, List<Integer> l2) -> {
          l1.addAll(l2);
          return l1;
        });

    return numbers;
  }

  public static List<Integer> streamToListViaCollect() {
    Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();

    return stream.collect(Collectors.toList());
  }
}
