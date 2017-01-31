package lambdasinaction.chap14.lazy;

import java.util.function.Predicate;

interface MyList<T> {
  T head();

  MyList<T> tail();

  default boolean isEmpty() {
    return true;
  }

  MyList<T> filter(Predicate<T> p);
}