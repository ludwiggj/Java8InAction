package lambdasinaction.chap14.lazy;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

class ReallyLazyList<T> implements MyList<T> {
  final T head;
  final Supplier<MyList<T>> tail;
  private Optional<MyList<T>> alreadyComputed = Optional.empty();

  public ReallyLazyList(T head, Supplier<MyList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  public T head() {
    return head;
  }

  public MyList<T> tail() {
    if (! alreadyComputed.isPresent()) {
      alreadyComputed = Optional.of(tail.get());
    }
    return alreadyComputed.get();
  }

  public boolean isEmpty() {
    return false;
  }

  public MyList<T> filter(Predicate<T> p) {
    return isEmpty() ? this : p.test(head()) ?
        new ReallyLazyList<>(head(), () -> tail().filter(p)) : tail().filter(p);
  }

  public static ReallyLazyList<Integer> from(int n) {
    return new ReallyLazyList<>(n, () -> from(n + 1));
  }

  public static ReallyLazyList<Integer> fromWithDebug(int n) {
    return new ReallyLazyList<>(n, () -> {
      System.out.println("Tail called");
      return fromWithDebug(n + 1);
    });
  }

  public static MyList<Integer> primes(MyList<Integer> numbers) {
    return new ReallyLazyList<>(
        numbers.head(),
        () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0))
    );
  }
}