package lambdasinaction.chap14.lazy;

import java.util.function.Predicate;

class Empty<T> implements MyList<T> {
  public T head() {
    throw new UnsupportedOperationException();
  }

  public MyList<T> tail() {
    throw new UnsupportedOperationException();
  }

  public MyList<T> filter(Predicate<T> p) {
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Empty)
      return true;
    else
      return false;
  }

  @Override
  public int hashCode() {
    return -12345678;
  }

  @Override
  public String toString() {
    return "<>";
  }
}