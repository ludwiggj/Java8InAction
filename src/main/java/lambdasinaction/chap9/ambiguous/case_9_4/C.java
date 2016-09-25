package lambdasinaction.chap9.ambiguous.case_9_4;

import lambdasinaction.chap9.ambiguous.A;
import lambdasinaction.chap9.ambiguous.B;

public class C implements B, A {

  public static void main(String... args) {

    // Hello from B - most specific default providing interface wins
    new C().hello();
  }
}