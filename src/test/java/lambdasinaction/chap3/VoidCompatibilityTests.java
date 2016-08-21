package lambdasinaction.chap3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VoidCompatibilityTests {

  // Special void-compatibility rule
  // If a lambda has a statement expression as its body, it’s compatible with a function
  // descriptor that returns void (provided the parameter list is compatible too). For
  // example, both of the following lines are legal even though the method add of a List
  // returns a boolean and not void as expected in the Consumer context (T -> void)
  @Test
  @DisplayName("Take 1, doesn't work")
  public void testVoidCompatibilityRule1() {
    // Need to reassign list crteated by following statement, as it does not support add method
    List<Integer> temp_list = Arrays.asList(1, 2, 3);

    List<Integer> list = new ArrayList<>(temp_list);

    // Predicate has a boolean return
    java.util.function.Predicate<Integer> p = i -> list.add(i);

    // Consumer has a void return
    Consumer<Integer> b = i -> list.add(i);

    p.test(4);
    assertThat(list, is(Arrays.asList(1, 2, 3, 4)));

    b.accept(5);
    assertThat(list, is(Arrays.asList(1, 2, 3, 4, 5)));
  }

  @Test
  @DisplayName("Take 2, does work")
  public void testVoidCompatibilityRul2() {
    ArrayList<Integer> list = new ArrayList<Integer>() {{
      add(1);
      add(2);
      add(3);
    }};

    // Predicate has a boolean return
    java.util.function.Predicate<Integer> p = i -> list.add(i);

    // Consumer has a void return
    Consumer<Integer> b = i -> list.add(i);

    p.test(4);
    assertThat(list, is(Arrays.asList(1, 2, 3, 4)));

    b.accept(5);
    assertThat(list, is(Arrays.asList(1, 2, 3, 4, 5)));
  }
}