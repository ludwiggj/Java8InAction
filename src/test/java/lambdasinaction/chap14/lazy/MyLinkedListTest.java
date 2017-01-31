package lambdasinaction.chap14.lazy;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MyLinkedListTest {

  @Test
  public void shouldFilterList() {

    MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));

    assertThat(l.filter(x -> x % 10 == 0), is(equalTo(new MyLinkedList<>(10, new Empty<>()))));

    assertThat(l.filter(x -> x % 5 == 0), is(equalTo(new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>())))));

    assertThat(l.filter(x -> x % 3 == 0), is(equalTo(new Empty<>())));
  }
}