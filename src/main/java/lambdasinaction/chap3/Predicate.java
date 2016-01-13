package lambdasinaction.chap3;

@FunctionalInterface
public interface Predicate<T> {
  boolean test(T t);
}