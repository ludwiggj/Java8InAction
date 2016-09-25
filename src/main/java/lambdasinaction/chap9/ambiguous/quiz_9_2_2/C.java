package lambdasinaction.chap9.ambiguous.quiz_9_2_2;

import lambdasinaction.chap9.ambiguous.A;
import lambdasinaction.chap9.ambiguous.B;

public class C extends D implements B, A {

  public static void main(String... args) {
    // Hello from C
    // D is abstract, C is forced to implement A even though versions are available in A and B
    new C().hello();
  }

  @Override
  public void hello() {
    System.out.println("Hello from C");
  }
}