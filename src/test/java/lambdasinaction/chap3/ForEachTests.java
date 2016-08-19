package lambdasinaction.chap3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("For each")
public class ForEachTests {

  @FunctionalInterface
  public interface Consumer<T>{
      void accept(T t);
  }

  private static <T> void forEach(List<T> list, Consumer<T> c) {
    for (T i : list) {
      c.accept(i);
    }
  }

  @Test
  @DisplayName("Concatenate strings")
  public void shouldConcatenateStrings() {
    final String[] sum = {""};

    forEach(Arrays.asList(1, 2, 3, 4, 5), i -> sum[0] += i);
    assertThat(sum[0], is("12345"));
  }
}