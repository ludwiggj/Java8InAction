package lambdasinaction.chap14;

import java.util.function.Function;

public class Combinators {
  static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
    return x -> g.apply(f.apply(x));
  }

  static <A> Function<A, A> repeat(int n, Function<A, A> f) {
    return n == 0 ? x -> x : compose(f, repeat(n - 1, f));
  }
}