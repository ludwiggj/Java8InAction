package lambdasinaction.chap9.ambiguous.quiz_9_2_1;

import lambdasinaction.chap9.ambiguous.A;
import lambdasinaction.chap9.ambiguous.B;

public class C extends D implements B, A {

  public static void main(String... args) {
    // Hello from D - method declaration from a superclass has priority
    new C().hello();
  }
}