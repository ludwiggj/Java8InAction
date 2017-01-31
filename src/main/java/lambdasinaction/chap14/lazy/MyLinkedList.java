package lambdasinaction.chap14.lazy;

import java.util.Objects;
import java.util.function.Predicate;

class MyLinkedList<T> implements MyList<T> {
  final T head;
  final MyList<T> tail;

  public MyLinkedList(T head, MyList<T> tail) {
    this.head = head;
    this.tail = tail;
  }

  public T head() {
    return head;
  }

  public MyList<T> tail() {
    return tail;
  }

  public boolean isEmpty() {
    return false;
  }

  public MyList<T> filter(Predicate<T> p) {
    return isEmpty() ? this : p.test(head()) ?
        new MyLinkedList<>(head(), tail().filter(p)) : tail().filter(p);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MyLinkedList)) return false;
    MyLinkedList<?> that = (MyLinkedList<?>) o;
    return Objects.equals(head, that.head) &&
        Objects.equals(tail, that.tail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(head, tail);
  }

  @Override
  public String toString() {
    return "(" + head.toString() + ") -> [" + tail.toString() + "]";
  }
}