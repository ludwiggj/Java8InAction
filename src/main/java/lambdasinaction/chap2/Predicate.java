package lambdasinaction.chap2;

// Take 7, abstract over list type
public interface Predicate<T> {
  boolean test(T t);
}