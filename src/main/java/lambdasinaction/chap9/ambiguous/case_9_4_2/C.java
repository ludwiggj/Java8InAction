package lambdasinaction.chap9.ambiguous.case_9_4_2;

import lambdasinaction.chap9.ambiguous.A;
import lambdasinaction.chap9.ambiguous.B;

public class C extends D implements B, A {

  public static void main(String... args) {
    // Hello from B - most specific default providing interface wins
    new C().hello();
  }
}