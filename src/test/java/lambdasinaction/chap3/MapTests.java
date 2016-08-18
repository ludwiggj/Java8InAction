package lambdasinaction.chap3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Map")
public class MapTests {

  private static <T, R> List<R> map(List<T> list, Function<T, R> f) {
    List<R> result = new ArrayList<>();
    for (T s : list) {
      result.add(f.apply(s));
    }
    return result;
  }

  @Test
  @DisplayName("Map strings to lengths")
   public void shouldMapStringsToLengths() {
     assertThat(map(Arrays.asList("lambdas", "in", "action"), s -> s.length()), is(Arrays.asList(7, 2, 6)));
   }
}